package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.offroad.feature.home.R

@Composable
internal fun AuthScreen(
    padding: PaddingValues,
    onAuthBtnClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Main1
    ) {
        ConstraintLayout {

            val (logo, kakaoLogin, googleLogin) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_auth_logo),
                contentDescription = "auth_logo",
                modifier = Modifier
                    .constrainAs(logo) {
                        start.linkTo(parent.start, margin = 112.dp)
                        end.linkTo(parent.end, margin = 112.dp)
                        top.linkTo(parent.top, margin = 231.72.dp)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.btn_auth_kakao_login),
                contentDescription = "auth_kakao",
                modifier = Modifier
                    .constrainAs(kakaoLogin) {
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                        top.linkTo(logo.bottom, margin = 38.dp)
                    }
            )
            ClickableImage(
                painter = painterResource(id = R.drawable.btn_auth_google_login),
                contentDescription = "auth_google",
                onClick = onAuthBtnClick,
                modifier = Modifier
                    .constrainAs(googleLogin) {
                        start.linkTo(parent.start, margin = 24.dp)
                        end.linkTo(parent.end, margin = 24.dp)
                        top.linkTo(kakaoLogin.bottom, margin = 14.dp)
                    }
            )
        }
    }
}

@Composable
fun ClickableImage(
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}