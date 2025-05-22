package com.teamoffroad.feature.recommendplace.presentation.component

import android.view.Gravity
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
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
import com.teamoffroad.core.designsystem.component.FullLinearLoadingAnimation
import com.teamoffroad.core.designsystem.component.StaticAnimationWrapper
import com.teamoffroad.core.designsystem.theme.Black
import com.teamoffroad.core.designsystem.theme.Black55
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub2
import com.teamoffroad.feature.explore.presentation.ExploreViewModel
import com.teamoffroad.feature.explore.presentation.component.ExploreInfoWindow
import com.teamoffroad.feature.explore.presentation.component.ExploreRefreshButton
import com.teamoffroad.feature.explore.presentation.component.ExploreTrackingButton
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.LocationModel
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import com.teamoffroad.feature.explore.presentation.util.ExploreAuthStateHandler
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceViewModel
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState
import com.teamoffroad.offroad.feature.recommendplace.R

@Composable
fun HasRecommendPlaceMapItems(
    recommendationsUiState: PlaceRecommendationsUiState,
    recommendPlaceViewModel: RecommendPlaceViewModel,
) {
    val context = LocalContext.current

    if (recommendationsUiState.isError) {
        Toast.makeText(
            context,
            stringResource(com.teamoffroad.offroad.feature.explore.R.string.explore_places_failed),
            Toast.LENGTH_SHORT
        ).show()
    }

//    ExploreRecommendPlaceMap(
//        locationState = recommendationsUiState.locationModel,
//        places = recommendationsUiState.recommendations,
//        selectedPlace = recommendationsUiState.selectedPlace,
//        updateLocation = recommendPlaceViewModel::updateLocation,
//        updateTrackingToggle = recommendPlaceViewModel::updateTrackingToggle,
//        updateSelectedPlace = recommendPlaceViewModel::updateSelectedPlace,
//        updatePlaces = recommendPlaceViewModel::updatePlaces,
//    )

    FullLinearLoadingAnimation(isLoading = recommendationsUiState.isLoading)
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun ExploreRecommendPlaceMap(
    locationState: LocationModel,
    places: List<PlaceModel>,
    selectedPlace: PlaceModel?,
    updateLocation: (Double, Double) -> Unit,
    updateTrackingToggle: (Boolean) -> Unit,
    updateSelectedPlace: (PlaceModel?) -> Unit,
    updatePlaces: (Double, Double) -> Unit,
) {
    val density = LocalDensity.current
    var markerOffset by remember { mutableStateOf(IntOffset.Zero) }
    var mapViewSize by remember { mutableStateOf(IntSize.Zero) }
    var infoWindowHeight by remember { mutableStateOf(182.dp) }
    val animatedInfoWindowHeight by animateDpAsState(
        targetValue = infoWindowHeight,
        animationSpec = tween(durationMillis = 500),
        label = "",
    )

    LaunchedEffect(locationState.cameraPositionState.cameraUpdateReason) {
        if (locationState.cameraPositionState.cameraUpdateReason == CameraUpdateReason.GESTURE) {
            updateTrackingToggle(true)
        }
    }

    LaunchedEffect(animatedInfoWindowHeight) {
        if (selectedPlace != null) {
            val offsetResult =
                getMarkerOffset(
                    selectedPlace.location,
                    locationState.cameraPositionState,
                    density,
                    mapViewSize,
                    animatedInfoWindowHeight,
                )
            markerOffset = offsetResult.first
        }
    }

    Box(
        Modifier
            .background(Main1)
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                mapViewSize = coordinates.size
            }) {
        StaticAnimationWrapper {
            Column {
                NaverMap(
                    properties = locationState.mapProperties,
                    uiSettings = MapUiSettings(
                        isScaleBarEnabled = false,
                        isZoomControlEnabled = false,
                        isLogoClickEnabled = false,
                        isCompassEnabled = false,
                        logoGravity = Gravity.TOP,
                        logoMargin = PaddingValues(top = 28.dp, start = 22.dp),
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
                        icon = OverlayImage.fromResource(com.teamoffroad.offroad.feature.explore.R.drawable.ic_explore_location_overlay),
                        subIcon = locationState.subIcon,
                        subIconWidth = 48,
                        subIconHeight = 40,
                        circleColor = Sub2.copy(alpha = locationState.circleAlpha),
                    )

                    places.forEach { place ->
                        Marker(
                            state = MarkerState(position = place.location),
                            icon = OverlayImage.fromResource(com.teamoffroad.offroad.feature.explore.R.drawable.ic_explore_marker),
                            onClick = {
                                val offsetResult = getMarkerOffset(
                                    place.location,
                                    locationState.cameraPositionState,
                                    density,
                                    mapViewSize,
                                    infoWindowHeight,
                                )
                                markerOffset = offsetResult.first
                                val newLatLng = getAdjustedLocationFromMarkerOffset(
                                    offsetResult.second,
                                    locationState.cameraPositionState,
                                    mapViewSize,
                                )
                                newLatLng?.let {
                                    locationState.cameraPositionState.move(
                                        CameraUpdate.scrollTo(it).animate(
                                            CameraAnimation.Easing, 500
                                        )
                                    )
                                }
                                updateSelectedPlace(place)
                                true
                            },
                        )
                    }

                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp)
                .align(Alignment.TopCenter),
        ) {
            if (locationState.isUserTrackingEnabled.not()) {
                ExploreRefreshButton(
                    text = stringResource(com.teamoffroad.offroad.feature.explore.R.string.explore_map_refresh),
                    onClick = {
                        updatePlaces(
                            locationState.cameraPositionState.position.target.latitude,
                            locationState.cameraPositionState.position.target.longitude,
                        )
                    },
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            ExploreTrackingButton(
                isTrackingEnabled = locationState.isUserTrackingEnabled,
                onClick = updateTrackingToggle,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 22.dp),
            )
        }


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
                    .offset { markerOffset }
                    .onGloballyPositioned { coordinates ->
                        with(density) {
                            infoWindowHeight = coordinates.size.height.toDp() - 20.dp
                        }
                    }) {
                    RecommendPlaceMapInfoWindow(
                        title = place.name,
                        shortIntroduction = place.shortIntroduction,
                        address = place.address,
                        visitCount = place.visitCount,
                        categoryImage = place.categoryImageUrl,
                        place = place,
                        onButtonClick = {
//                            updateExploreResult(
//                                place.id,
//                                locationState.location.latitude,
//                                locationState.location.longitude,
//                                place.placeCategory,
//                            )
                            updateSelectedPlace(null)
                        },
                        onCloseButtonClick = {
                            updateSelectedPlace(null)
                        },
                        modifier = Modifier
                            .align(Alignment.TopCenter),
                    )
                }
            }
        }
    }
}

private fun getMarkerOffset(
    location: LatLng,
    cameraPositionState: CameraPositionState,
    density: Density,
    screenSize: IntSize,
    infoWindowHeight: Dp,
): Pair<IntOffset, IntOffset> {
    val screenPosition = cameraPositionState.projection?.toScreenLocation(location)
    val infoWindowWith = 230.dp

    return screenPosition?.let {
        with(density) {
            var xOffset = (it.x.toDp() - infoWindowWith / 2).roundToPx()
            var yOffset = (it.y.toDp() - infoWindowHeight + 28.dp).roundToPx()

            var xCameraMove = 0
            var yCameraMove = 0

            if (xOffset < 0) {
                xCameraMove = xOffset - 11.dp.roundToPx()
                xOffset = 28
            }
            if (xOffset + infoWindowWith.roundToPx() > screenSize.width) {
                xCameraMove =
                    (xOffset + infoWindowWith.roundToPx() + 10.dp.roundToPx()) - screenSize.width
                xOffset = screenSize.width - infoWindowWith.roundToPx() - 10.dp.roundToPx()
            }

            if (yOffset < 120.dp.roundToPx()) {
                yCameraMove = yOffset - 88.dp.roundToPx()
                yOffset = 232
            }
            if (yOffset + infoWindowHeight.roundToPx() > screenSize.height) {
                yCameraMove = (yOffset + infoWindowHeight.roundToPx()) - screenSize.height
                yOffset = screenSize.height - infoWindowHeight.roundToPx()
            }

            Pair(IntOffset(xOffset, yOffset), IntOffset(xCameraMove, yCameraMove))
        }
    } ?: Pair(IntOffset(0, 0), IntOffset(0, 0))
}

private fun getAdjustedLocationFromMarkerOffset(
    offset: IntOffset,
    cameraPositionState: CameraPositionState,
    screenSize: IntSize,
): LatLng? {
    val contentBounds = cameraPositionState.contentBounds ?: return null
    val targetPosition = cameraPositionState.position.target

    val northEast = contentBounds.northEast
    val southWest = contentBounds.southWest

    var lat = targetPosition.latitude
    var lng = targetPosition.longitude

    if (offset.x != 0) {
        val lngPerPixel = (northEast.longitude - southWest.longitude) / screenSize.width
        lng += lngPerPixel * offset.x
    }

    if (offset.y != 0) {
        val latPerPixel = (northEast.latitude - southWest.latitude) / screenSize.height
        lat -= latPerPixel * offset.y
    }

    return if (lat != targetPosition.latitude || lng != targetPosition.longitude) {
        LatLng(lat, lng)
    } else null
}

@Composable
fun NoRecommendPlaceMapItems() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 52.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_recommend_sad),
            contentDescription = null,
        )
        Text(
            text = stringResource(id = R.string.recommend_place_map_no_place),
            style = OffroadTheme.typography.boxMedi,
            color = Black55,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 26.dp),
            lineHeight = 16.sp
        )
        Text(
            text = stringResource(id = R.string.recommend_place_map_no_place_reply),
            style = OffroadTheme.typography.btnSmall,
            color = Main1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 76.dp)
                .padding(top = 24.dp)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(46.dp),
                    color = Main2
                )
                .background(
                    shape = RoundedCornerShape(46.dp),
                    color = Main2
                )
                .padding(vertical = 14.dp),
            textAlign = TextAlign.Center
        )
    }
}