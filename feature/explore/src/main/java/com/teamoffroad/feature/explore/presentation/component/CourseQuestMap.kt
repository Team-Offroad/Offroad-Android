package com.teamoffroad.feature.explore.presentation.component

import android.view.Gravity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.createBitmap
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import com.teamoffroad.core.designsystem.theme.Transparent
import com.teamoffroad.feature.explore.domain.model.Location
import com.teamoffroad.feature.explore.domain.model.Location.Companion.toLatLng
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlacesUiModel

private const val DEFAULT_MAP_ZOOM: Double = 13.5

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun CourseQuestMap(
    modifier: Modifier = Modifier,
    mapHeight: Dp,
    mapHeightPx: MutableFloatState,
    location: Location,
    places: CourseQuestPlacesUiModel,
    onLocationChange: (Double, Double) -> Unit,
) {
    val context = LocalContext.current
    val cameraPositionState = remember { CameraPositionState() }

    LaunchedEffect(places.centerLocation) {
        cameraPositionState.position =
            CameraPosition(
                places.centerLocation.toLatLng(),
                DEFAULT_MAP_ZOOM,
            )
    }

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .height(mapHeight)
                .onGloballyPositioned { layoutCoordinates ->
                    mapHeightPx.floatValue = layoutCoordinates.size.height.toFloat()
                },
    ) {
        NaverMap(
            properties = MapProperties(locationTrackingMode = LocationTrackingMode.NoFollow),
            uiSettings =
                MapUiSettings(
                    isScaleBarEnabled = false,
                    isZoomControlEnabled = false,
                    isLogoClickEnabled = false,
                    isCompassEnabled = false,
                    logoGravity = Gravity.TOP,
                    logoMargin = PaddingValues(top = 28.dp, start = 22.dp),
                ),
            locationSource = rememberFusedLocationSource(isCompassEnabled = true),
            cameraPositionState = cameraPositionState,
            onLocationChange = { onLocationChange(it.latitude, it.longitude) },
            modifier = Modifier.fillMaxSize(),
        ) {
            LocationOverlay(
                position = location.toLatLng(),
                icon = OverlayImage.fromBitmap(createBitmap(1, 1)),
                circleColor = Transparent,
            )

            places.places.forEach { place ->
                Marker(
                    state = MarkerState(position = place.position.toLatLng()),
                    icon = OverlayImage.fromBitmap(getCategoryOverlayImage(context, place.category)),
                )
            }
        }
    }
}
