package com.teamoffroad.feature.mypage.presentation.component

sealed interface SettingDialogState {
    data object InVisible : SettingDialogState
    data object MarketingVisible : SettingDialogState
    data object LogoutVisible : SettingDialogState
    data object WithDrawVisible : SettingDialogState
}