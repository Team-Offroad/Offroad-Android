package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.feature.explore.presentation.component.ExploreCamera
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraNavigateBack
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraOverlay
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState

@Composable
internal fun ExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    navigateToExplore: (String, String) -> Unit,
    viewModel: ExploreCameraViewModel = hiltViewModel(),
) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val successImageUrl by viewModel.successImageUrl.collectAsStateWithLifecycle()

    when (uiState) {
        ExploreCameraUiState.Success -> navigateToExplore(ExploreCameraUiState.Success.toString(), successImageUrl)
        ExploreCameraUiState.CodeError -> navigateToExplore(ExploreCameraUiState.CodeError.toString(), successImageUrl)
        ExploreCameraUiState.LocationError -> navigateToExplore(ExploreCameraUiState.LocationError.toString(), successImageUrl)
        ExploreCameraUiState.EtcError -> navigateToExplore(ExploreCameraUiState.EtcError.toString(), successImageUrl)
        else -> {}
    }

    ExploreCamera(
        uiState = uiState,
        localContext = localContext,
        placeId = placeId,
        latitude = latitude,
        longitude = longitude,
        lifecycleOwner = lifecycleOwner,
        postExploreResult = viewModel::postExploreResult
    )
    Spacer(
        modifier = Modifier
            .height(20.dp)
            .fillMaxWidth()
            .background(Black.copy(alpha = 0.44f))
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        OffroadActionBar(Black.copy(alpha = 0.44f))
        ExploreCameraNavigateBack(navigateToExplore)
        ExploreCameraOverlay()
    }
}
