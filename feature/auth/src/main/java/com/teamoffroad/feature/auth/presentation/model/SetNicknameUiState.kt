package com.teamoffroad.feature.auth.presentation.model

import com.teamoffroad.feature.auth.presentation.component.NicknameValidateResult

data class SetNicknameUiState(
    val nickname: String = "",
    val nicknameValidateResult: NicknameValidateResult = NicknameValidateResult.Empty,
)
