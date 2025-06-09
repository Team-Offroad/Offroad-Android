package com.teamoffroad.feature.main.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.teamoffroad.core.designsystem.component.ChangeBottomBarColor
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.offroad.feature.main.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffects, lifecycleOwner) {
        viewModel.sideEffects.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SplashSideEffect.NavigateToHome -> navigateToHome()
                    is SplashSideEffect.NavigateLogin -> navigateToSignIn()
                }
            }
    }

    LaunchedEffect(Unit) {
        viewModel.checkAutoSignIn()
    }

    val scale = remember { Animatable(1f) }
    val alpha = remember { Animatable(1f) }

    ChangeBottomBarColor(Main2)
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
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Main2)
            .navigationPadding()
            .graphicsLayer {
                this.alpha = alpha.value
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AnimatedVisibility(
            visible = true,
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            Image(
                modifier = Modifier
                    .height(138.dp)
                    .scale(scale.value)
                    .graphicsLayer { this.alpha = alpha.value },
                painter = painterResource(R.drawable.ic_splash_logo),
                contentDescription = "splash",
                contentScale = ContentScale.FillHeight,
            )
        }
    }
}