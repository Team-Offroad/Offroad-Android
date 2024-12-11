package com.teamoffroad.feature.auth.presentation.signup

data class SignUpState(
    val s: Boolean = false
)

sealed interface SignUpSideEffect {
    data object Empty : SignUpSideEffect
    data object Loading : SignUpSideEffect
    data object Error : SignUpSideEffect
    data object Success : SignUpSideEffect
}