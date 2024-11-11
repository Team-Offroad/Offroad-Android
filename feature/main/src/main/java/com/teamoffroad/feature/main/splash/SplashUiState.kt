package com.teamoffroad.feature.main.splash

sealed class SplashUiState {
    data object NavigateHome: SplashUiState()
    data object NavigateLogin: SplashUiState()
}
