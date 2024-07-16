package com.teamoffroad.feature.auth.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun SetNicknameRoute(
    padding: PaddingValues,
    navigateToSetBirthDate: () -> Unit,
    onboardingViewModel: OnboardingViewModel = hiltViewModel()
) {
    SetNicknameScreen(
        padding = padding,
        navigateToSetBirthDate = navigateToSetBirthDate,
        viewModel = onboardingViewModel
    )
}