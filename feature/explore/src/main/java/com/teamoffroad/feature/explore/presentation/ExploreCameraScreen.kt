package com.teamoffroad.feature.explore.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.component.ExploreCamera
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraOverlay
import com.teamoffroad.feature.explore.presentation.model.ExploreResultState

@Composable
internal fun ExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    navigateToExplore: (String, String) -> Unit,
    exploreCameraViewModel: ExploreCameraViewModel = hiltViewModel(),
) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by exploreCameraViewModel.exploreResultState.collectAsStateWithLifecycle()
    val resultImageUrl by exploreCameraViewModel.resultImageUrl.collectAsStateWithLifecycle()

    BackHandler {
        navigateToExplore("", "")
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            ExploreResultState.Success -> navigateToExplore(ExploreResultState.Success.toString(), resultImageUrl)
            ExploreResultState.CodeError -> navigateToExplore(ExploreResultState.CodeError.toString(), resultImageUrl)
            ExploreResultState.LocationError -> navigateToExplore(ExploreResultState.LocationError.toString(), resultImageUrl)
            ExploreResultState.EtcError -> navigateToExplore(ExploreResultState.EtcError.toString(), resultImageUrl)
            else -> {}
        }
    }

    ExploreCamera(
        uiState = uiState,
        localContext = localContext,
        placeId = placeId,
        latitude = latitude,
        longitude = longitude,
        lifecycleOwner = lifecycleOwner,
        postExploreResult = exploreCameraViewModel::postExploreResult
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(
            modifier = Modifier
                .height(38.dp)
                .fillMaxWidth()
                .background(Black.copy(alpha = 0.44f))
        )
        NavigateBackAppBar(
            mainColor = White,
            backgroundColor = Black.copy(alpha = 0.44f),
        ) {
            navigateToExplore("", "")
        }
        ExploreCameraOverlay()
    }
}
