package com.teamoffroad.feature.home.presentation.component.dialog

data class CustomAlertDialogState(
    val title: String = "",
    val description: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
