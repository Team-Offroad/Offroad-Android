package com.teamoffroad.feature.auth.presentation.model

data class AuthUiState(
    val empty: Boolean = false,
    val signInFail: Boolean = false,
    val isAutoSignIn: Boolean = false,
    val signInSuccess: Boolean = false,
    val alreadyExist: Boolean = false,

    )
