package com.teamoffroad.feature.mypage.presentation.component

sealed interface SettingDialogState {
    data object inVisible : SettingDialogState
    data object marketingVisible : SettingDialogState
    data object logoutVisible : SettingDialogState
    data object withDrawVisible : SettingDialogState
}