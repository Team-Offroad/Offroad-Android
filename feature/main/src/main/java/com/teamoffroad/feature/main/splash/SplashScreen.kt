package com.teamoffroad.feature.main.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.teamoffroad.core.designsystem.component.ChangeBottomBarColor
import com.teamoffroad.core.designsystem.theme.Main2
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navigateToAuth: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(Unit) {
        viewModel.showSplash()
    }
    LaunchedEffect(viewModel.splashUiState, lifecycleOwner) {
        viewModel.splashUiState.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { splashUiState ->
                when (splashUiState) {
                    is SplashUiState.NavigateHome -> navigateToHome()
                    is SplashUiState.NavigateLogin -> navigateToAuth()
                }
            }
    }

    ChangeBottomBarColor(Main2)
    var backgroundVisibility by remember { mutableStateOf(true) }
    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        delay(200L)
        launch {
            delay(600L)
            scale.animateTo(
                targetValue = 0.9f,
                animationSpec = tween(
                    durationMillis = 1500,
                )
            )
        }
        launch {
            delay(600L)
            alpha.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 1300
                )
            )
        }
        delay(1300L)
        backgroundVisibility = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Main2)
            .graphicsLayer {
                this.alpha = alpha.value
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.45f))
        AnimatedVisibility(
            visible = backgroundVisibility,
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            Image(
                modifier = Modifier
                    .scale(scale.value)
                    .graphicsLayer { this.alpha = alpha.value },
                painter = painterResource(com.teamoffroad.offroad.feature.main.R.drawable.ic_splash_app_logo),
                contentDescription = "splash"
            )
        }
        AnimatedVisibility(
            visible = !backgroundVisibility,
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Main2)
            )
        }
        Spacer(modifier = Modifier.fillMaxHeight(0.55f))
    }
}