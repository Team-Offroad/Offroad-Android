package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Kakao
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.auth.R

@Composable
internal fun AuthScreen(
    padding: PaddingValues,
    navigateToSetNickname: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1
    ) {
        ConstraintLayout {

            val (appLogo, kakaoLogin, googleLogin) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_auth_logo),
                contentDescription = "auth_logo",
                modifier = Modifier
                    .constrainAs(appLogo) {
                        start.linkTo(parent.start, margin = 112.dp)
                        end.linkTo(parent.end, margin = 112.dp)
                        top.linkTo(parent.top, margin = 232.dp)
                        bottom.linkTo(parent.bottom, margin = 432.dp)
                    }
            )
            ClickableImage(
                text = "Kakao로 계속하기",
                textColor = Color.Black,
                painter = painterResource(id = R.drawable.ic_auth_kakao_logo),
                background = Kakao,
                contentDescription = "auth_kakao",
                onClick = navigateToSetNickname,
                modifier = Modifier
                    .constrainAs(kakaoLogin) {
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                        top.linkTo(appLogo.bottom, margin = 38.dp)
                        bottom.linkTo(parent.bottom, margin = 388.dp)
                    }
            )
            ClickableImage(
                text = "Google로 계속하기",
                textColor = Main2,
                painter = painterResource(id = R.drawable.ic_auth_google_logo),
                background = White,
                contentDescription = "auth_google",
                onClick = navigateToSetNickname,
                modifier = Modifier
                    .constrainAs(googleLogin) {
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                        top.linkTo(kakaoLogin.bottom, margin = 14.dp)
                        bottom.linkTo(parent.bottom, margin = 320.dp)
                    }
            )
        }
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
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
            .background(color = background)
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