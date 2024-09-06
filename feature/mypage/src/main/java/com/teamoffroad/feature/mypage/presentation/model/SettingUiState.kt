package com.teamoffroad.feature.mypage.presentation.model

import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState

data class SettingUiState(
    var dialogVisible: SettingDialogState = SettingDialogState.inVisible,
    val withDrawInputState: String = "",
    val withDrawResult: Boolean = false,
)