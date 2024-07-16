package com.teamoffroad.feature.home.presentation.model

data class CustomTitleDialogStateModel(
    val emblemCode: String = "",
    val emblemName: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
