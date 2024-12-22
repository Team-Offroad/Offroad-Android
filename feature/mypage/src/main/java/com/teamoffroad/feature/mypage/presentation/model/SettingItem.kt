package com.teamoffroad.feature.mypage.presentation.model

data class SettingItem(
    val title: String,
    val isImportant: Boolean,
    val onClick: () -> Unit,
)
