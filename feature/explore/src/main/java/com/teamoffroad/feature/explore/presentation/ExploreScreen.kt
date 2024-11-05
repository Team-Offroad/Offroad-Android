package com.teamoffroad.feature.explore.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    qrResultImageUrl: String?,
    navigateToHome: (String, List<String>) -> Unit,
    navigateToPlace: () -> Unit,
    navigateToQuest: () -> Unit,
    exploreViewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState: ExploreUiState by exploreViewModel.uiState.collectAsStateWithLifecycle()
    var mapKey by remember { mutableIntStateOf(0) }

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                mapKey++
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(authResultState) {
        if (authResultState != null) {
            exploreViewModel.updateExploreAuthState(ExploreAuthState.from(authResultState))
        }
    }

    LaunchedEffect(uiState.locationModel.location) {
        if (uiState.places.isEmpty()) exploreViewModel.updatePlaces()
    }

    if (uiState.isUpdatePlacesFailed) {
        Toast.makeText(context, stringResource(R.string.explore_places_failed), Toast.LENGTH_SHORT).show()
    }

    ExploreAuthStateHandler(
        uiState,
        exploreViewModel::updateExploreAuthState,
        qrResultImageUrl,
        navigateToHome,
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
                    exploreViewModel::updateExploreAuthState,
                    exploreViewModel::isValidDistance,
                    exploreViewModel::updateExploreResult,
                    mapKey,
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
}
