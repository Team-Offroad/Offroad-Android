package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable

@Composable
internal fun SetGenderRoute(
    navigateToSetCharacter: () -> Unit,
) {
    SetGenderScreen(
        navigateToSetCharacter = navigateToSetCharacter
    )
}