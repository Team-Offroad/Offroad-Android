package com.teamoffroad.feature.explore.presentation

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.GestureNavigation
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.explore.presentation.component.ExploreCamera
import com.teamoffroad.feature.explore.presentation.component.ExploreCameraOverlay
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.offroad.feature.explore.R

@Composable
internal fun ExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    navigateToExplore: (String, String) -> Unit,
    navigateToBack: () -> Unit,
    exploreCameraViewModel: ExploreCameraViewModel = hiltViewModel(),
) {
    val localContext = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val uiState by exploreCameraViewModel.exploreAuthState.collectAsStateWithLifecycle()
    val resultImageUrl by exploreCameraViewModel.resultImageUrl.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        when (uiState) {
            is ExploreAuthState.Success -> navigateToExplore(ExploreAuthState.Success().toString(), resultImageUrl)
            ExploreAuthState.CodeError -> navigateToExplore(ExploreAuthState.CodeError.toString(), resultImageUrl)
            ExploreAuthState.LocationError -> navigateToExplore(ExploreAuthState.LocationError.toString(), resultImageUrl)
            ExploreAuthState.EtcError -> navigateToExplore(ExploreAuthState.EtcError.toString(), resultImageUrl)
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
            .then(GestureNavigation())
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
            text = stringResource(R.string.explore_navigate_back),
        ) {
            navigateToBack()
        }
        ExploreCameraOverlay()
    }
}
