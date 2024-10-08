package com.teamoffroad.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.offroad.feature.auth.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SplashScreen() {
    var backgroundVisibility by remember { mutableStateOf(true) }
    val scale = remember { Animatable(1f) }
    val offsetY = remember { Animatable(0f) }
    val alpha = remember { Animatable(1f) } // 초기 alpha 값을 1f로 설정

    LaunchedEffect(Unit) {
        delay(300L)
        launch {
            delay(600L)
            scale.animateTo(
                targetValue = 0.8f,
                animationSpec = tween(
                    durationMillis = 1500,
                )
            )
        }
        launch {
            delay(800L)
            offsetY.animateTo(
                targetValue = -320f,
                animationSpec = tween(
                    durationMillis = 750,
                )
            )
        }

        launch {
            delay(800L)
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 600
                )
            )
        }
        delay(1500L)
        backgroundVisibility = false
    }

    Column {
        AnimatedVisibility(
            visible = backgroundVisibility,
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        this.alpha = alpha.value
                    },
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Main2)
                )
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .scale(scale.value)
                        .offset {
                            IntOffset(0, offsetY.value.roundToInt())
                        },
                    painter = painterResource(id = R.drawable.ic_splash_logo),
                    contentDescription = "splash"
                )
            }
        }
    }
}