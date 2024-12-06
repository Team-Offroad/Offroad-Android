package com.teamoffroad.feature.auth.presentation.model

data class AuthUiState(
    val empty: Boolean = true,
    val signInSuccess: Boolean = false,
    val alreadyExist: Boolean = false,
    val startKakaoSignIn: Boolean = false,
    val startGoogleSignIn: Boolean = false,
    val isAutoSignIn: Boolean = false,
)

