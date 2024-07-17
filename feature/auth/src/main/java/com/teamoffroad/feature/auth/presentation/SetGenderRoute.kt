package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SetGenderRoute(
    navigateToSetCharacter: () -> Unit,
    setGenderViewModel: SetGenderViewModel = hiltViewModel()
) {
    SetGenderScreen(
        navigateToSetCharacter = navigateToSetCharacter,
        viewModel = setGenderViewModel,
    )
}