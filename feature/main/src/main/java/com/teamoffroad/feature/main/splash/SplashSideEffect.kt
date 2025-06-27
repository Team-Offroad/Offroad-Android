package com.teamoffroad.feature.main.splash

sealed interface SplashSideEffect {
    data object NavigateLogin : SplashSideEffect
    data object NavigateToHome : SplashSideEffect
}