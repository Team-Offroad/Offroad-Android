package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
internal fun SetNicknameRoute(
    padding: PaddingValues,
    onHomeBtnClick: () -> Unit,
) {
    SetNicknameScreen(
        padding = padding,
        navigateToHome = onHomeBtnClick
    )
}