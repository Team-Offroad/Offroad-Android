package com.teamoffroad.feature.home.presentation.model

data class HomeUserChangeEmblemDialogStateModel(
    val emblemCode: String = "",
    val emblemName: String = "",
    val onClickConfirm: () -> Unit = {},
    val onClickCancel: () -> Unit = {},
)
