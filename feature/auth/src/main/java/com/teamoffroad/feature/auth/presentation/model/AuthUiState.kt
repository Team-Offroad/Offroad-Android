package com.teamoffroad.feature.auth.presentation.model

data class AuthUiState(
    val empty: Boolean = false,
    val fail: Boolean = false,
    val autoSignIn: Boolean = false,
    val authSignIn: Boolean = false,
    val alreadyExist: Boolean = false,

)

