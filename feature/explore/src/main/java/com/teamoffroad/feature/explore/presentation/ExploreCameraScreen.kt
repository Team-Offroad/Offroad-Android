package com.teamoffroad.feature.explore.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.teamoffroad.feature.explore.presentation.component.ExploreCamera
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraOverlay

@Composable
internal fun ExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    viewModel: ExploreCameraViewModel = hiltViewModel(),
) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    ExploreCamera(
        localContext = localContext,
        placeId = placeId,
        latitude = latitude,
        longitude = longitude,
        lifecycleOwner = lifecycleOwner,
        postExploreResult = viewModel::postExploreResult
    )
    ExploreCameraOverlay()
}
