package com.teamoffroad.feature.auth.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SetCharacterRoute(
    navigateToHome: () -> Unit,
    setCharacterViewModel: SetCharacterViewModel = hiltViewModel(),
) {
    SetCharacterScreen(
        navigateToHome = navigateToHome,
        viewModel = setCharacterViewModel,
    )
}
