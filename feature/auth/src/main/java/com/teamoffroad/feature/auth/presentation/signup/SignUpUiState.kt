package com.teamoffroad.feature.auth.presentation.signup

data class SignUpState(
    val nickname: String = "",
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val date: String = "",
    val selectedGender: String? = null,
    val nicknameScreenResult: Boolean = false,
    val birthDateScreenResult: Boolean = false,
    val genderScreenResult: Boolean = false,

    )

sealed interface SignUpSideEffect {
    data object Empty : SignUpSideEffect
    data object Loading : SignUpSideEffect
    data object Error : SignUpSideEffect
    data object Success : SignUpSideEffect
}