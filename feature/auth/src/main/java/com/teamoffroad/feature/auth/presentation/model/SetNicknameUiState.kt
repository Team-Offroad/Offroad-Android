package com.teamoffroad.feature.auth.presentation.model

data class SetNicknameUiState(
    val nickname: String = "",
    val nicknameValidateResult: NicknameValidateResult = NicknameValidateResult.Empty,
)
