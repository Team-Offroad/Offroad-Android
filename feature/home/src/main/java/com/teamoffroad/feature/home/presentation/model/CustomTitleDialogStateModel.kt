package com.teamoffroad.feature.home.presentation.model

data class CustomTitleDialogStateModel( // 나중에 구조 보고 변경 예정
    val title: String = "",
    val description: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
