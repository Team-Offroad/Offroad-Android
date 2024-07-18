package com.teamoffroad.feature.auth.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    var backgroundVisibility by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000L)
        backgroundVisibility = false
    }

    AnimatedVisibility(
        visible = backgroundVisibility,
        enter = EnterTransition.None,
        exit = fadeOut(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Main2),
        ) {
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_splash_logo),
                contentDescription = "splash"
            )
        }
    }
}