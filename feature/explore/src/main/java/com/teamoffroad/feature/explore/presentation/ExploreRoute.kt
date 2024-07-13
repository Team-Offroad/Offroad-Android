package com.teamoffroad.feature.explore.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState

@Composable
internal fun ExploreRoute(
    navigateToHome: () -> Unit,
    viewModel: ExploreViewModel = hiltViewModel(),
) {

    val uiState: ExploreUiState by viewModel.uiState.collectAsStateWithLifecycle()

    ExploreScreen(
        uiState = uiState,
        navigateToHome = navigateToHome,
        updatePermission = viewModel::updatePermission,
        updateLocation = viewModel::updateLocation,
        updateTrackingToggle = viewModel::updateTrackingToggle,
    )
}
