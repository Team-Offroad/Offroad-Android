package com.teamoffroad.feature.auth.presentation.model

import com.teamoffroad.feature.auth.presentation.component.ValidateResult

data class SetNicknameUiState(
    val nickname: String = "",
    val validateResult: ValidateResult = ValidateResult.Empty,
)
