package com.teamoffroad.feature.auth.presentation

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.teamoffroad.core.designsystem.component.ChangeBottomBarColor
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.auth.R
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.collectLatest

@Composable
internal fun AuthScreen(
    navigateToHome: () -> Unit,
    navigateToAgreeTermsAndConditions: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel(),
) {
    val isAuthUiState by viewModel.authUiState.collectAsStateWithLifecycle()
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            viewModel.performGoogleSignIn(task)
        }
    }
    val context = LocalContext.current as ComponentActivity
    val entryPoint =
        EntryPointAccessors.fromActivity<OAuthEntryPoint>(context)
    val oAuthInteractor = entryPoint.getOAuthInteractor()

    LaunchedEffect(Unit) {
        viewModel.checkAutoSignIn()
    }
    LaunchedEffect(isAuthUiState) {
        when {
            isAuthUiState.isAutoSignIn -> navigateToHome()
            isAuthUiState.signInSuccess && !isAuthUiState.alreadyExist -> viewModel.updateSignInResult()
            isAuthUiState.signInSuccess && isAuthUiState.alreadyExist -> navigateToHome()
            isAuthUiState.kakaoSignIn -> {
                val result = oAuthInteractor.signInKakao()
                result.onSuccess {
                    viewModel.performKakaoSignIn(it.accessToken)
                }.onFailure {
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                true -> {
                    viewModel.initState()
                    navigateToAgreeTermsAndConditions()
                }

                false -> {}
            }
        }
    }
    Column(
        modifier = Modifier
            .navigationPadding()
            .background(Main1)
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OffroadActionBar()
        ChangeBottomBarColor(Main1)
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 228.dp, bottom = 30.dp),
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "auth_logo",
        )
        ClickableImage(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.explore_auth_kakao),
            textColor = Black,
            painter = painterResource(id = R.drawable.ic_auth_kakao_logo),
            background = Kakao,
            contentDescription = "auth_kakao",
            onClick = { viewModel.startKakaoSignIn() },
        )
        ClickableImage(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(R.string.explore_auth_google),
            textColor = Main2,
            painter = painterResource(id = R.drawable.ic_auth_google_logo),
            background = White,
            contentDescription = "auth_google",
            onClick = {
                googleSignInLauncher.launch(viewModel.googleSignInClient.signInIntent)
            },
        )
        Spacer(modifier = Modifier.weight(1f))
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
            .fillMaxWidth()
            .padding(vertical = 7.dp)
            .clickable(onClick = onClick)
            .background(
                color = background,
                shape = RoundedCornerShape(6.dp)
            ),
    ) {
        Image(
            modifier = Modifier
                .padding(start = 30.dp)
                .align(Alignment.CenterStart),
            painter = painter,
            contentDescription = contentDescription,
        )
        Text(
            modifier = modifier
                .padding(vertical = 18.dp),
            textAlign = TextAlign.Center,
            text = text,
            color = textColor,
            style = OffroadTheme.typography.bothLogin,
        )
    }
}