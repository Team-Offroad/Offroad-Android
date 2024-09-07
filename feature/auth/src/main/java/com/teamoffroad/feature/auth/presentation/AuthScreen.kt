package com.teamoffroad.feature.auth.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.browser.customtabs.CustomTabsIntent
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.delay

@Composable
internal fun AuthScreen(
    navigateToHome: () -> Unit,
    navigateToAgreeTermsAndConditions: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
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
        if (isAutoSignIn && signInLauncherInitialized) {
            googleSignInLauncher.launch(viewModel.googleSignInClient.signInIntent)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
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
                onClick = {},
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


