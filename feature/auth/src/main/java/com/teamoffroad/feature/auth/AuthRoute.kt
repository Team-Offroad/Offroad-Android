package com.teamoffroad.feature.auth

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
internal fun AuthRoute(
    padding: PaddingValues,
    onAuthBtnClick: () -> Unit,
) {
    AuthScreen(
        padding = padding,
        onAuthBtnClick = onAuthBtnClick
    )
}