package com.teamoffroad.feature.auth.presentation.component

sealed interface NicknameValidateResult {
    data object Empty : NicknameValidateResult
    data object Error : NicknameValidateResult
    data object NicknameValidateSuccess : NicknameValidateResult
    data object NicknameValidateFailure : NicknameValidateResult
    data object Duplicate : NicknameValidateResult
    data object Success : NicknameValidateResult
}

