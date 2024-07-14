package com.teamoffroad.feature.explore.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.view.Gravity
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.CameraUpdateReason
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.presentation.component.CustomInfoWindow
import com.teamoffroad.feature.explore.presentation.component.ExploreAppBar
import com.teamoffroad.feature.explore.presentation.component.ExploreMapBottomButton
import com.teamoffroad.feature.explore.presentation.component.ExploreMapForeground
import com.teamoffroad.feature.explore.presentation.component.ExploreRefreshButton
import com.teamoffroad.feature.explore.presentation.component.ExploreTrackingButton
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.LocationModel
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import com.teamoffroad.offroad.feature.explore.R
import kotlin.math.roundToInt

@Composable
internal fun ExploreScreen(
    uiState: ExploreUiState,
    navigateToHome: () -> Unit,
    updatePermission: (Boolean, Boolean) -> Unit,
    updateLocation: (Double, Double) -> Unit,
    updateTrackingToggle: (Boolean) -> Unit,
    updatePlaces: () -> Unit,
) {

    val context = LocalContext.current

    val permissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    val launcherMultiplePermissions = getLocationPermissionLauncher(updatePermission)

    updatePlaces()

    ExplorePermissionsHandler(
        context = context,
        uiState = uiState,
        launcherMultiplePermissions = launcherMultiplePermissions,
        permissions = permissions,
        updatePermission = updatePermission,
    )

    if (uiState.isAlreadyHavePermission) {
        ExploreNaverMap(uiState.locationModel, uiState.places, updateLocation, updateTrackingToggle)
    }
    if (uiState.isPermissionRejected) {
        ExplorePermissionRejectedHandler(context, navigateToHome)
    }
    ExploreAppBar()
}

@Composable
private fun getLocationPermissionLauncher(
    updatePermission: (Boolean, Boolean) -> Unit,
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val isPermissionGranted = permissionsMap.values.all { it }
        when (isPermissionGranted) {
            true -> updatePermission(true, false)
            false -> updatePermission(false, true)
        }
    }
}

@Composable
private fun ExplorePermissionsHandler(
    context: Context,
    uiState: ExploreUiState,
    launcherMultiplePermissions: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
    permissions: Array<String>,
    updatePermission: (Boolean, Boolean) -> Unit,
) {
    LaunchedEffect(uiState.isAlreadyHavePermission) {
        if (uiState.isAlreadyHavePermission) return@LaunchedEffect

        val isFineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isCoarseLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        when (!isFineLocationGranted || !isCoarseLocationGranted) {
            true -> launcherMultiplePermissions.launch(permissions)
            false -> updatePermission(true, false)
        }
    }
}

@Composable
private fun ExplorePermissionRejectedHandler(
    context: Context,
    navigateToHome: () -> Unit,
) {
    Toast.makeText(
        context, stringResource(R.string.explore_location_permission_failed), Toast.LENGTH_SHORT
    ).show()
    navigateToHome()
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun ExploreNaverMap(
    locationState: LocationModel,
    placeModel: List<PlaceModel>,
    updateLocation: (Double, Double) -> Unit,
    updateTrackingToggle: (Boolean) -> Unit,
) {
    val cameraPositionState by rememberUpdatedState(newValue = locationState.cameraPositionState)
    val mapProperties by rememberUpdatedState(newValue = locationState.mapProperties)
    val location by rememberUpdatedState(newValue = locationState.location)
    val subIcon by rememberUpdatedState(newValue = locationState.subIcon)
    val places by rememberUpdatedState(newValue = placeModel)

    var selectedPlace by remember { mutableStateOf<PlaceModel?>(null) }
    var markerOffset by remember { mutableStateOf(IntOffset(0, 0)) }

    LaunchedEffect(cameraPositionState.cameraUpdateReason) {
        if (cameraPositionState.cameraUpdateReason == CameraUpdateReason.GESTURE) {
            selectedPlace = null
            updateTrackingToggle(true)
        }
    }

    Box(
        Modifier.fillMaxSize().padding(bottom = 74.dp)
    ) {
        NaverMap(
            properties = mapProperties,
            uiSettings = MapUiSettings(
                isScaleBarEnabled = false,
                isZoomControlEnabled = false,
                isLogoClickEnabled = false,
                logoGravity = Gravity.TOP,
                // TODO: 로고 이미지 수정
                logoMargin = PaddingValues(top = 0.dp, start = 22.dp),
            ),
            locationSource = rememberFusedLocationSource(isCompassEnabled = true),
            cameraPositionState = cameraPositionState,
            onLocationChange = { location ->
                updateLocation(location.latitude, location.longitude)
            },
            onMapClick = { _, _ ->
                selectedPlace = null
            },
        ) {
            LocationOverlay(
                position = location,
                icon = OverlayImage.fromResource(R.drawable.ic_explore_location_overlay),
                subIcon = subIcon,
                subIconWidth = 48,
                subIconHeight = 40,
                circleColor = Sub2.copy(alpha = locationState.circleAlpha),
            )
            places.forEach { place ->
                Marker(
                    state = MarkerState(position = place.location),
                    icon = OverlayImage.fromResource(R.drawable.ic_explore_place_marker),
                    onClick = {
                        selectedPlace = place
                        markerOffset = calculateMarkerOffset(place.location, cameraPositionState)
                        true
                    },
                )
            }
        }
        selectedPlace?.let { place ->
            Box(modifier = Modifier.align(Alignment.TopStart).offset { markerOffset }) {
                CustomInfoWindow(
                    title = place.name,
                    shortIntroduction = place.shortIntroduction,
                    address = place.address,
                    visitCount = place.visitCount,
                    categoryImage = place.categoryImage,
                    onButtonClick = {
                        // Button click action
                    },
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        }
        ExploreMapForeground()
        Box(
            modifier = Modifier.fillMaxWidth().padding(top = 96.dp).align(Alignment.TopCenter),
        ) {
            ExploreRefreshButton(
                text = "현 지도에서 검색",
                onClick = {},
                modifier = Modifier.align(Alignment.Center),
            )
            ExploreTrackingButton(
                isTrackingEnabled = locationState.isUserTrackingEnabled,
                onClick = updateTrackingToggle,
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 22.dp),
            )
        }
        Row(
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            ExploreMapBottomButton(
                painter = painterResource(R.drawable.ic_explore_quest_list),
                text = "퀘스트 목록",
                onClick = {},
            )
            Spacer(modifier = Modifier.size(16.dp))
            ExploreMapBottomButton(
                painter = painterResource(R.drawable.ic_explore_location),
                text = "장소 목록",
                onClick = {},
            )
        }
    }
}

private fun calculateMarkerOffset(location: LatLng, cameraPositionState: CameraPositionState): IntOffset {
    val screenPosition = cameraPositionState.projection?.toScreenLocation(location)
    return screenPosition?.let {
        IntOffset(it.x.roundToInt() - 350, (it.y.roundToInt()) - 640)
    } ?: IntOffset(0, 0)
}