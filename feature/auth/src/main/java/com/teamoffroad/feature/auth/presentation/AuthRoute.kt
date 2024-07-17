package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable

@Composable
internal fun AuthRoute(
    navigateToHome: () -> Unit,
    navigateToSetNickname: () -> Unit,
) {

    AuthScreen(
        navigateToHome = navigateToHome,
        navigateToSetNickname = navigateToSetNickname
    )
}
