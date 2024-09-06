package com.teamoffroad.feature.explore.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.feature.explore.presentation.component.ExploreOffroadMap
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.util.ExploreAuthStateHandler
import com.teamoffroad.feature.explore.presentation.util.ExplorePermissionHandler
import com.teamoffroad.feature.explore.presentation.util.ExplorePermissionRejectedHandler
import com.teamoffroad.offroad.feature.explore.R

@Composable
internal fun ExploreScreen(
    authResultState: String?,
    qrResultImageUrl: String?,
    navigateToHome: (String) -> Unit,
    navigateToExploreCameraScreen: (Long, Double, Double) -> Unit,
    navigateToPlace: () -> Unit,
    navigateToQuest: () -> Unit,
    exploreViewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState: ExploreUiState by exploreViewModel.uiState.collectAsStateWithLifecycle()
    val locationResultImageUrl: String by exploreViewModel.successImageUrl.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(authResultState) {
        if (authResultState != null) {
            exploreViewModel.updateExploreAuthState(ExploreAuthState.from(authResultState))
        }
    }

    LaunchedEffect(Unit) {
        exploreViewModel.updatePlaces()
    }

    if (!uiState.loading && uiState.places.isEmpty()) exploreViewModel.updatePlaces()
    if (uiState.isUpdatePlacesFailed) {
        Toast.makeText(context, stringResource(R.string.explore_places_failed), Toast.LENGTH_SHORT).show()
    }

    ExploreAuthStateHandler(
        uiState,
        exploreViewModel::updateExploreAuthState,
        qrResultImageUrl,
        locationResultImageUrl,
        navigateToHome,
    )

    ExplorePermissionHandler(
        context = context,
        uiState = uiState,
        updatePermission = exploreViewModel::updatePermission,
    )

    if (uiState.permissionModel.isSomePermissionRejected == true) {
        ExplorePermissionRejectedHandler(
            context = context,
            uiState = uiState,
            navigateToHome = { navigateToHome(PlaceCategory.NONE.name) },
            updatePermission = exploreViewModel::updatePermission,
        )
    }

    if (uiState.permissionModel.isAllPermissionGranted) {
        ExploreOffroadMap(
            uiState.locationModel,
            uiState.places,
            uiState.selectedPlace,
            navigateToExploreCameraScreen,
            navigateToPlace,
            navigateToQuest,
            exploreViewModel::updateLocation,
            exploreViewModel::updateTrackingToggle,
            exploreViewModel::updateSelectedPlace,
            exploreViewModel::updatePlaces,
            exploreViewModel::updateExploreAuthState,
            exploreViewModel::isValidDistance,
            exploreViewModel::updateExploreResult,
        )
    }
}
