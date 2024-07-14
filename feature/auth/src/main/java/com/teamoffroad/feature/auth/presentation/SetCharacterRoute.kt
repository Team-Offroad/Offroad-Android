package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable

@Composable
internal fun SetCharacterRoute(
    navigateToHome: () -> Unit,
) {
    SetCharacterScreen(
        navigateToHome = navigateToHome
    )
}