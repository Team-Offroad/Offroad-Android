package com.teamoffroad.feature.explore.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.feature.explore.presentation.component.ExploreOffroadMap
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.util.ExploreCameraUiStateHandler
import com.teamoffroad.feature.explore.presentation.util.ExploreLocationPermissionHandler
import com.teamoffroad.feature.explore.presentation.util.ExplorePermissionRejectedHandler
import com.teamoffroad.offroad.feature.explore.R

@Composable
internal fun ExploreScreen(
    authResultType: String?,
    qrResultImageUrl: String?,
    navigateToHome: (String) -> Unit,
    navigateToExploreCameraScreen: (Long, Double, Double) -> Unit,
    navigateToPlace: () -> Unit,
    exploreViewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState: ExploreUiState by exploreViewModel.uiState.collectAsStateWithLifecycle()
    val locationResultImageUrl: String by exploreViewModel.successImageUrl.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(authResultType) {
        if (authResultType != null) {
            exploreViewModel.updateExploreCameraUiState(uiState.getExploreCameraUiState(authResultType))
            exploreViewModel.updatePlaces()
        }
    }

    LaunchedEffect(Unit) {
        exploreViewModel.updatePlaces()
    }

    if (!uiState.loading && uiState.places.isEmpty()) exploreViewModel.updatePlaces()
    if (uiState.isUpdatePlacesFailed) {
        Toast.makeText(context, stringResource(R.string.explore_places_failed), Toast.LENGTH_SHORT).show()
    }

    ExploreCameraUiStateHandler(
        uiState,
        exploreViewModel::updateExploreCameraUiState,
        qrResultImageUrl,
        exploreViewModel,
        locationResultImageUrl,
        navigateToHome,
    )

    ExploreLocationPermissionHandler(
        context = context,
        uiState = uiState,
        updatePermission = exploreViewModel::updatePermission,
    )

    if (uiState.isSomePermissionRejected == true) {
        ExplorePermissionRejectedHandler(
            context = context,
            uiState = uiState,
            navigateToHome = { navigateToHome("") },
            updatePermission = exploreViewModel::updatePermission,
        )
    }

    if (uiState.isAllPermissionGranted) {
        ExploreOffroadMap(
            uiState.locationModel,
            uiState.places,
            uiState.selectedPlace,
            navigateToExploreCameraScreen,
            navigateToPlace,
            exploreViewModel::updateLocation,
            exploreViewModel::updateTrackingToggle,
            exploreViewModel::updateSelectedPlace,
            exploreViewModel::updateCategory,
            exploreViewModel::updatePlaces,
            exploreViewModel::updateExploreCameraUiState,
            exploreViewModel::isValidDistance,
            exploreViewModel::postExploreResult,
        )
    }

    OnBackButtonListener()
}
