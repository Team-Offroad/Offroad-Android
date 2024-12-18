package com.teamoffroad.feature.explore.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.teamoffroad.core.designsystem.component.FullLinearLoadingAnimation
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
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
    navigateToHome: (String, List<String>) -> Unit,
    navigateToPlace: (String, String) -> Unit,
    navigateToQuest: () -> Unit,
    exploreViewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState: ExploreUiState by exploreViewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(authResultState) {
        if (authResultState != null) {
            exploreViewModel.updateExploreAuthState(ExploreAuthState.from(authResultState))
        }
    }

    if (uiState.isUpdatePlacesFailed) {
        Toast.makeText(context, stringResource(R.string.explore_places_failed), Toast.LENGTH_SHORT).show()
    }

    ExploreAuthStateHandler(
        uiState = uiState,
        updateExploreAuthState = exploreViewModel::updateExploreAuthState,
        navigateToHome = navigateToHome,
    )

    ExplorePermissionHandler(
        context = context,
        uiState = uiState,
        updatePermission = exploreViewModel::updatePermission,
    )

    uiState.isLocationPermissionGranted.let { isLocationPermissionGranted ->
        when (isLocationPermissionGranted) {
            true -> StaticAnimationWrapper {
                ExploreOffroadMap(
                    uiState.locationModel,
                    uiState.places,
                    uiState.selectedPlace,
                    navigateToPlace,
                    navigateToQuest,
                    exploreViewModel::updateLocation,
                    exploreViewModel::updateTrackingToggle,
                    exploreViewModel::updateSelectedPlace,
                    exploreViewModel::updatePlaces,
                    exploreViewModel::updateExploreResult,
                )
            }

            false -> ExplorePermissionRejectedHandler(
                context = context,
                navigateToHome = { navigateToHome(PlaceCategory.NONE.name, emptyList()) },
                updatePermission = exploreViewModel::updatePermission,
            )

            else -> Unit
        }
    }

    FullLinearLoadingAnimation(isLoading = uiState.loading)
}
