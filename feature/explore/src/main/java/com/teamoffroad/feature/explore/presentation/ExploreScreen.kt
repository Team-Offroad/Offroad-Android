package com.teamoffroad.feature.explore.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.view.Gravity
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.teamoffroad.core.common.util.OnBackButtonListener
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.presentation.component.ExploreAppBar
import com.teamoffroad.feature.explore.presentation.component.ExploreFailedDialogContent
import com.teamoffroad.feature.explore.presentation.component.ExploreInfoWindow
import com.teamoffroad.feature.explore.presentation.component.ExploreMapBottomButton
import com.teamoffroad.feature.explore.presentation.component.ExploreMapForeground
import com.teamoffroad.feature.explore.presentation.component.ExploreRefreshButton
import com.teamoffroad.feature.explore.presentation.component.ExploreResultDialog
import com.teamoffroad.feature.explore.presentation.component.ExploreSuccessDialogContent
import com.teamoffroad.feature.explore.presentation.component.ExploreTrackingButton
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.LocationModel
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import com.teamoffroad.offroad.feature.explore.R

@Composable
internal fun ExploreScreen(
    errorType: String,
    successImageUrl: String,
    navigateToHome: (String) -> Unit,
    navigateToExploreCameraScreen: (Long, Double, Double) -> Unit,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val uiState: ExploreUiState by viewModel.uiState.collectAsStateWithLifecycle()
    val locationSuccessImageUrl: String by viewModel.successImageUrl.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(errorType) {
        viewModel.updateExploreCameraUiState(uiState.getExploreCameraUiState(errorType))
    }

    if (!uiState.loading && uiState.places.isEmpty()) viewModel.updatePlaces()
    if (uiState.isUpdatePlacesFailed) {
        Toast.makeText(context, stringResource(R.string.explore_places_failed), Toast.LENGTH_SHORT)
            .show()
    }

    ExploreCameraUiStateHandler(
        uiState,
        viewModel::updateExploreCameraUiState,
        successImageUrl,
        viewModel,
        navigateToHome,
        locationSuccessImageUrl,
    )

    ExploreLocationPermissionHandler(
        context = context,
        uiState = uiState,
        updatePermission = viewModel::updatePermission,
    )

    if (uiState.isSomePermissionRejected == true) {
        ExplorePermissionRejectedHandler(
            context = context,
            uiState = uiState,
            navigateToHome = { navigateToHome("") },
            updatePermission = viewModel::updatePermission,
        )
    }

    if (uiState.isAllPermissionGranted) {
        ExploreNaverMap(
            uiState.locationModel,
            uiState.places,
            uiState.selectedPlace,
            navigateToHome,
            navigateToExploreCameraScreen,
            viewModel::updateLocation,
            viewModel::updateTrackingToggle,
            viewModel::updateSelectedPlace,
            viewModel::updateCategory,
            viewModel::updatePlaces,
            viewModel::updateExploreCameraUiState,
            viewModel::isValidDistance,
            viewModel::postExploreResult,
        )
    }

    OnBackButtonListener()
}

@Composable
private fun ExploreCameraUiStateHandler(
    uiState: ExploreUiState,
    updateExploreCameraUiState: (ExploreCameraUiState) -> Unit,
    qrSuccessImageUrl: String,
    viewModel: ExploreViewModel,
    navigateToHome: (String) -> Unit,
    locationSuccessImageUrl: String,
) {
    val imgUrl = locationSuccessImageUrl.ifBlank {
        qrSuccessImageUrl
    }
    when (uiState.errorType) {
        ExploreCameraUiState.LocationError -> {
            ExploreResultDialog(
                errorType = ExploreCameraUiState.LocationError,
                text = stringResource(R.string.explore_location_failed_label),
                content = { ExploreFailedDialogContent(painter = painterResource(R.drawable.ic_explore_error_location)) },
                onDismissRequest = { updateExploreCameraUiState(ExploreCameraUiState.None) }
            )
        }

        ExploreCameraUiState.CodeError -> {
            ExploreResultDialog(
                errorType = ExploreCameraUiState.CodeError,
                text = stringResource(R.string.explore_code_failed_label),
                content = { ExploreFailedDialogContent(painter = painterResource(R.drawable.ic_explore_error_code)) },
                onDismissRequest = { updateExploreCameraUiState(ExploreCameraUiState.None) }
            )
        }

        ExploreCameraUiState.EtcError -> {
            ExploreResultDialog(
                errorType = ExploreCameraUiState.EtcError,
                text = stringResource(R.string.explore_etc_failed_label),
                content = { ExploreFailedDialogContent(painter = null) },
                onDismissRequest = { updateExploreCameraUiState(ExploreCameraUiState.None) }
            )
        }

        ExploreCameraUiState.Success -> {
            val category = viewModel.category.collectAsStateWithLifecycle().value

            ExploreResultDialog(
                errorType = ExploreCameraUiState.Success,
                text = stringResource(R.string.explore_dialog_success),
                content = { ExploreSuccessDialogContent(url = imgUrl) },
                onDismissRequest = { navigateToHome(category) }
            )
        }

        else -> {}
    }
}

@Composable
private fun ExploreLocationPermissionHandler(
    context: Context,
    uiState: ExploreUiState,
    updatePermission: (Boolean?, Boolean, Boolean) -> Unit,
) {
    val launcherLocationPermissions = getPermissionsLauncher(updatePermission)

    LaunchedEffect(uiState.isAllPermissionGranted) {
        val isFineLocationGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isCameraGranted = ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

        if (!isFineLocationGranted || !isCameraGranted) {
            launcherLocationPermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA,
                )
            )
        } else {
            updatePermission(null, true, true)
        }
    }
}

@Composable
private fun getPermissionsLauncher(
    updatePermission: (Boolean?, Boolean, Boolean) -> Unit,
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {
    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { isPermissionGranted ->
        val isLocationNotGranted =
            isPermissionGranted[Manifest.permission.ACCESS_FINE_LOCATION] == false
        val isCameraNotGranted = isPermissionGranted[Manifest.permission.CAMERA] == false

        if (isPermissionGranted.values.all { it }) {
            updatePermission(false, true, true)
        }

        when {
            isCameraNotGranted -> updatePermission(true, true, false)

            isLocationNotGranted -> updatePermission(true, false, true)
        }
    }
}

@Composable
private fun ExplorePermissionRejectedHandler(
    context: Context,
    uiState: ExploreUiState,
    navigateToHome: (String?) -> Unit,
    updatePermission: (Boolean?, Boolean, Boolean) -> Unit,
) {
    val toastMessage = when {
        !uiState.isCameraPermissionGranted -> stringResource(R.string.explore_camera_permission_failed)

        !uiState.isLocationPermissionGranted -> stringResource(R.string.explore_location_permission_failed)

        else -> ""
    }

    if (toastMessage.isNotBlank()) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        updatePermission(null, false, false)
        navigateToHome(null)
    }
}


@OptIn(ExperimentalNaverMapApi::class)
@Composable
private fun ExploreNaverMap(
    locationState: LocationModel,
    places: List<PlaceModel>,
    selectedPlace: PlaceModel?,
    navigateToHome: (String) -> Unit,
    navigateToExploreCameraScreen: (Long, Double, Double) -> Unit,
    updateLocation: (Double, Double) -> Unit,
    updateTrackingToggle: (Boolean) -> Unit,
    updateSelectedPlace: (PlaceModel?) -> Unit,
    updateCategory: (String) -> Unit,
    updatePlaces: (Double, Double) -> Unit,
    updateCameraUiState: (ExploreCameraUiState) -> Unit,
    isValidDistance: (PlaceModel, LatLng) -> Boolean,
    postExploreResult: (Long, Double, Double) -> Unit,
) {
    val density = LocalDensity.current
    var markerOffset by remember { mutableStateOf(IntOffset.Zero) }
    var mapViewSize by remember { mutableStateOf(IntSize.Zero) }
    val backgroundPadding = 104

    LaunchedEffect(locationState.cameraPositionState.cameraUpdateReason) {
        if (locationState.cameraPositionState.cameraUpdateReason == CameraUpdateReason.GESTURE) {
            updateTrackingToggle(true)
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .padding(bottom = 74.dp)
            .navigationBarsPadding()
            .onGloballyPositioned { coordinates ->
                mapViewSize = coordinates.size
            }) {
        NaverMap(
            properties = locationState.mapProperties,
            uiSettings = MapUiSettings(
                isScaleBarEnabled = false,
                isZoomControlEnabled = false,
                isLogoClickEnabled = false,
                logoGravity = Gravity.TOP,
                // TODO: 릴리즈 이전에 로고 이미지 수정
                logoMargin = PaddingValues(top = 0.dp, start = 22.dp),
            ),
            locationSource = rememberFusedLocationSource(isCompassEnabled = true),
            cameraPositionState = locationState.cameraPositionState,
            onLocationChange = { location ->
                updateLocation(location.latitude, location.longitude)
            },
            onMapClick = { _, _ ->
                updateSelectedPlace(null)
            },
        ) {
            LocationOverlay(
                position = locationState.location,
                icon = OverlayImage.fromResource(R.drawable.ic_explore_location_overlay),
                subIcon = locationState.subIcon,
                subIconWidth = 48,
                subIconHeight = 40,
                circleColor = Sub2.copy(alpha = locationState.circleAlpha),
            )
            places.forEach { place ->
                Marker(
                    state = MarkerState(position = place.location),
                    icon = OverlayImage.fromResource(R.drawable.ic_explore_place_marker),
                    onClick = {
                        markerOffset = calculateMarkerOffset(
                            place.location,
                            locationState.cameraPositionState,
                            density,
                            mapViewSize
                        )
                        updateSelectedPlace(place)
                        updateCategory(place.placeCategory.name)
                        true
                    },
                )
            }
        }
        ExploreMapForeground()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = (backgroundPadding + 18).dp)
                .align(Alignment.TopCenter),
        ) {
            ExploreRefreshButton(
                text = stringResource(R.string.explore_map_refresh),
                onClick = {
                    updatePlaces(
                        locationState.cameraPositionState.position.target.latitude,
                        locationState.cameraPositionState.position.target.longitude,
                    )
                },
                modifier = Modifier.align(Alignment.Center),
            )
            ExploreTrackingButton(
                isTrackingEnabled = locationState.isUserTrackingEnabled,
                onClick = updateTrackingToggle,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 22.dp),
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            ExploreMapBottomButton(
                painter = painterResource(R.drawable.ic_explore_quest_list),
                text = stringResource(R.string.explore_quests),
                onClick = {},
            )
            Spacer(modifier = Modifier.size(16.dp))
            ExploreMapBottomButton(
                painter = painterResource(R.drawable.ic_explore_location),
                text = stringResource(R.string.explore_places),
                onClick = {},
            )
        }
        ExploreAppBar(backgroundPadding)
        selectedPlace?.let { place ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black.copy(alpha = 0.25f))
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = { updateSelectedPlace(null) })
                    }
            ) {
                Box(modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset { markerOffset }) {
                    ExploreInfoWindow(
                        title = place.name,
                        shortIntroduction = place.shortIntroduction,
                        address = place.address,
                        visitCount = place.visitCount,
                        categoryImage = place.categoryImageUrl,
                        onButtonClick = {
                            when (isValidDistance(place, locationState.location)) {
                                true -> {
                                    if (selectedPlace.placeCategory == PlaceCategory.CAFFE || selectedPlace.placeCategory == PlaceCategory.RESTAURANT) {
                                        navigateToExploreCameraScreen(
                                            place.id,
                                            locationState.location.latitude,
                                            locationState.location.longitude,
                                        )
                                    } else {
                                        postExploreResult(
                                            place.id,
                                            locationState.location.latitude,
                                            locationState.location.longitude,
                                        )
                                    }
                                }

                                false -> updateCameraUiState(ExploreCameraUiState.LocationError)
                            }
                            updateSelectedPlace(null)
                        },
                        onCloseButtonClick = {
                            updateSelectedPlace(null)
                        },
                        modifier = Modifier.align(Alignment.TopCenter),
                    )
                }
            }
        }
    }
}

private fun calculateMarkerOffset(
    location: LatLng,
    cameraPositionState: CameraPositionState,
    density: Density,
    screenSize: IntSize,
): IntOffset {
    val screenPosition = cameraPositionState.projection?.toScreenLocation(location)
    return screenPosition?.let {
        with(density) {
            var xOffset = (it.x.toDp() - 115.dp).roundToPx()
            var yOffset = (it.y.toDp() - 224.dp).roundToPx()

            if (xOffset < 0) {
                xOffset = 28
            }
            if (xOffset + 230.dp.roundToPx() > screenSize.width) {
                xOffset = screenSize.width - 714
            }

            if (yOffset < 120.dp.roundToPx()) {
                yOffset = 232
            }
            if (yOffset + 174.dp.roundToPx() > screenSize.height) {
                yOffset = screenSize.height - 174
            }

            IntOffset(xOffset, yOffset)
        }
    } ?: IntOffset(0, 0)
}
