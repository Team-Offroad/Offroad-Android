package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable

@Composable
internal fun AuthRoute(
    onAuthBtnClick: () -> Unit,
) {

    AuthScreen(
        navigateToSetNickname = onAuthBtnClick
    )
}
