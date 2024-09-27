package com.teamoffroad.feature.auth.presentation

import android.app.Activity
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.auth.domain.model.SocialSignInPlatform
import com.teamoffroad.offroad.feature.auth.BuildConfig
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.delay

@Composable
internal fun AuthScreen(
    navigateToHome: () -> Unit,
    navigateToAgreeTermsAndConditions: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val isSignInSuccess by viewModel.successSignIn.collectAsStateWithLifecycle()
    val isAutoSignIn by viewModel.autoSignIn.collectAsStateWithLifecycle()
    val isAlreadyExist by viewModel.alreadyExist.collectAsStateWithLifecycle()
    var signInLauncherInitialized by remember { mutableStateOf(false) }
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            viewModel.performGoogleSignIn(task)
        }
    }
    var showWebView by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        signInLauncherInitialized = true
    }
    LaunchedEffect(Unit) {
        delay(1200L)
        viewModel.checkAutoSignIn()
    }

    LaunchedEffect(isSignInSuccess) {
        if (isSignInSuccess && !isAlreadyExist) navigateToAgreeTermsAndConditions()
        if (isSignInSuccess && isAlreadyExist) navigateToHome()
    }
    LaunchedEffect(isAutoSignIn, signInLauncherInitialized) {
        Log.d("asdads", isAutoSignIn)
        if (isAutoSignIn == SocialSignInPlatform.GOOGLE.name && signInLauncherInitialized) {
            googleSignInLauncher.launch(viewModel.googleSignInClient.signInIntent)
        }
        else if (isAutoSignIn == SocialSignInPlatform.KAKAO.name && signInLauncherInitialized) {
            showWebView = true
        }
    }

    Surface(
        modifier = Modifier
            .navigationPadding()
            .fillMaxSize(),
        color = Main1
    ) {
        ConstraintLayout {
            val (appLogo, kakaoLogin, googleLogin) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_auth_logo),
                contentDescription = "auth_logo",
                modifier = Modifier.constrainAs(appLogo) {
                    start.linkTo(parent.start, margin = 112.dp)
                    end.linkTo(parent.end, margin = 112.dp)
                    top.linkTo(parent.top, margin = 232.dp)
                    bottom.linkTo(parent.bottom, margin = 432.dp)
                }
            )
            ClickableImage(
                text = stringResource(R.string.explore_auth_kakao),
                textColor = Black,
                painter = painterResource(id = R.drawable.ic_auth_kakao_logo),
                background = Kakao,
                contentDescription = "auth_kakao",
                onClick = { showWebView = true },
                modifier = Modifier.constrainAs(kakaoLogin) {
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    top.linkTo(appLogo.bottom, margin = 38.dp)
                }
            )
            ClickableImage(
                text = stringResource(R.string.explore_auth_google),
                textColor = Main2,
                painter = painterResource(id = R.drawable.ic_auth_google_logo),
                background = White,
                contentDescription = "auth_google",
                onClick = {
                    googleSignInLauncher.launch(viewModel.googleSignInClient.signInIntent)
                },
                modifier = Modifier.constrainAs(googleLogin) {
                    start.linkTo(parent.start, margin = 24.dp)
                    end.linkTo(parent.end, margin = 24.dp)
                    top.linkTo(kakaoLogin.bottom, margin = 14.dp)
                }
            )
        }
    }
    SplashScreen()
    if (showWebView) {
        StartKakaoLoginWebView(
            clientId = BuildConfig.KAKO_CLIENT_ID,
            redirectUri = BuildConfig.KAKO_REDIRECT_URI,
            onCodeReceived = { code ->
                viewModel.performKakaoSignIn(code)
                Log.d("asdasd", code)
            },
            onClose = {
                showWebView = false
            },
        )
    }
}

@Composable
fun ClickableImage(
    text: String,
    textColor: Color,
    painter: Painter,
    background: Color,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(
                color = background,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        ConstraintLayout {
            val (loginLogo, logoText) = createRefs()

            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .constrainAs(loginLogo) {
                        start.linkTo(parent.start, margin = 30.dp)
                        end.linkTo(parent.end, margin = 264.dp)
                        top.linkTo(parent.top, margin = 18.dp)
                        bottom.linkTo(parent.bottom, margin = 18.dp)
                    }
            )
            Text(
                text = text,
                color = textColor,
                style = OffroadTheme.typography.bothLogin,
                modifier = Modifier
                    .constrainAs(logoText) {
                        start.linkTo(parent.start, margin = 102.dp)
                        end.linkTo(parent.end, margin = 98.dp)
                        top.linkTo(parent.top, margin = 18.dp)
                        bottom.linkTo(parent.bottom, margin = 18.dp)
                    }
            )
        }
    }
}

@Composable
private fun StartKakaoLoginWebView(
    clientId: String,
    redirectUri: String,
    onCodeReceived: (String) -> Unit,
    onClose: () -> Unit,
) {
    val loginUrl =
        "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirectUri&prompt=select_account"

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = object : WebViewClient() {
                    override fun onPageStarted(
                        view: WebView?,
                        url: String?,
                        favicon: android.graphics.Bitmap?
                    ) {
                        super.onPageStarted(view, url, favicon)

                        url?.let {
                            if (it.startsWith(redirectUri)) {
                                val uri = android.net.Uri.parse(it)
                                val code = uri.getQueryParameter("code")
                                if (code != null) {
                                    onCodeReceived(code)
                                    onClose()
                                }
                            }
                        }
                    }

                    override fun shouldOverrideUrlLoading(
                        view: WebView?,
                        request: WebResourceRequest?
                    ): Boolean {
                        val url = request?.url.toString()
                        if (url.startsWith(redirectUri)) {
                            val uri = android.net.Uri.parse(url)
                            val code = uri.getQueryParameter("code")
                            if (code != null) {
                                onCodeReceived(code)
                                onClose()
                                return true
                            }
                        }
                        return super.shouldOverrideUrlLoading(view, request)
                    }
                }
                loadUrl(loginUrl)
            }
        }
    )
}