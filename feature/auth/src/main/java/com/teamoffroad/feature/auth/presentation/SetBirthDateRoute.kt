package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable

@Composable
internal fun SetBirthDateRoute(
    navigateToSetGender: () -> Unit,
) {
    SetBirthDateScreen(
        navigateToHome = navigateToSetGender
    )
}