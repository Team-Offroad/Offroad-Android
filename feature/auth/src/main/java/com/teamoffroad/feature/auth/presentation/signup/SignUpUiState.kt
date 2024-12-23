package com.teamoffroad.feature.auth.presentation.signup

import com.teamoffroad.feature.auth.presentation.model.DateValidateResult
import com.teamoffroad.feature.auth.presentation.model.NicknameValidateResult

data class SignUpState(
    val nickname: String = "",
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val date: String? = null,
    val focus: String = "none",
    val yearValidateResult: DateValidateResult = DateValidateResult.Empty,
    val monthValidateResult: DateValidateResult = DateValidateResult.Empty,
    val dayValidateResult: DateValidateResult = DateValidateResult.Empty,
    val selectedGender: String? = null,
    val nicknameScreenResult: NicknameValidateResult = NicknameValidateResult.Empty,
    val genderScreenResult: Boolean = false,
)

sealed interface SignUpSideEffect {
    data object NavigateSetCharacter : SignUpSideEffect
    data object FocusNext : SignUpSideEffect
    data object FocusClear : SignUpSideEffect
}