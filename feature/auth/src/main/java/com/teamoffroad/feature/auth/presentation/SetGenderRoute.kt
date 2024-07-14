package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable

@Composable
internal fun SetGenderRoute(
    navigateToHome: () -> Unit,
) {
    SetGenderScreen(
        navigateToHome = navigateToHome
    )
}