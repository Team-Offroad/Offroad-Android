package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SetBirthDateRoute(
    navigateToSetGender: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    SetBirthDateScreen(
        navigateToSetGender = navigateToSetGender,
        viewModel = authViewModel
    )
}