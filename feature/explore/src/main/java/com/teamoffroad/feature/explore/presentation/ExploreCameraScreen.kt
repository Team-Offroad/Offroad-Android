package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.feature.explore.presentation.component.ExploreCamera
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraNavigateBack
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraOverlay

@Composable
internal fun ExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    navigateToExplore: () -> Unit,
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
        ExploreCameraNavigateBack(navigateToExplore)
        ExploreCameraOverlay()
    }
}
