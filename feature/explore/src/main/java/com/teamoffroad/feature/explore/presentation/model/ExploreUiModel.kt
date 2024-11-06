package com.teamoffroad.feature.explore.presentation.model

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.overlay.OverlayImage
import com.teamoffroad.offroad.feature.explore.R

data class LocationModel(
    val location: LatLng = LatLng(
        DEFAULT_LOCATION_LATITUDE.toDouble(),
        DEFAULT_LOCATION_LONGITUDE.toDouble(),
    ),
    val previousLocation: LatLng = location,
    val isUserTrackingEnabled: Boolean = true,
    val mapProperties: MapProperties = MapProperties(locationTrackingMode = LocationTrackingMode.Follow),
    val cameraPositionState: CameraPositionState = CameraPositionState(CameraPosition(location, 15.0)),
    val subIcon: OverlayImage? = OverlayImage.fromResource(R.drawable.ic_explore_location_overlay_sub),
    val circleAlpha: Float = FOLLOW_CIRCLE_ALPHA,
) {

    fun updateLocation(latitude: Double, longitude: Double): LocationModel {
        return copy(
            location = LatLng(
                latitude,
                longitude,
            ),
        )
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean): LocationModel {
        return copy(
            isUserTrackingEnabled = !isUserTrackingEnabled,
            mapProperties = MapProperties(
                locationTrackingMode = getTrackingMode(!isUserTrackingEnabled),
            ),
            subIcon = getSubIconImage(!isUserTrackingEnabled),
            circleAlpha = getCircleAlpha(!isUserTrackingEnabled),
        )
    }

    private fun getTrackingMode(isUserTrackingEnabled: Boolean): LocationTrackingMode {
        return when (isUserTrackingEnabled) {
            true -> LocationTrackingMode.Follow
            false -> LocationTrackingMode.NoFollow
        }
    }

    private fun getSubIconImage(isUserTrackingEnabled: Boolean): OverlayImage? {
        return when (isUserTrackingEnabled) {
            true -> OverlayImage.fromResource(R.drawable.ic_explore_location_overlay_sub)
            false -> null
        }
    }

    private fun getCircleAlpha(isUserTrackingEnabled: Boolean): Float {
        return when (isUserTrackingEnabled) {
            true -> FOLLOW_CIRCLE_ALPHA
            false -> NO_FOLLOW_CIRCLE_ALPHA
        }
    }

    fun isUserMoveFarEnough(): Boolean {
        return location.distanceTo(previousLocation) > MAX_DISTANCE
    }

    fun updatePreviousLocation(location: LatLng): LocationModel {
        return copy(
            previousLocation = location,
        )
    }

    private companion object {
        private const val DEFAULT_LOCATION_LATITUDE = 37.588764f
        private const val DEFAULT_LOCATION_LONGITUDE = 127.05879f

        private const val FOLLOW_CIRCLE_ALPHA = 0.25f
        private const val NO_FOLLOW_CIRCLE_ALPHA = 0.07f

        private const val MAX_DISTANCE = 750
    }
}

data class PlaceModel(
    val id: Long,
    val name: String,
    val address: String,
    val shortIntroduction: String,
    val placeCategory: PlaceCategory,
    val placeArea: String,
    val categoryImageUrl: String,
    val location: LatLng,
    val visitCount: Int,
    val isVisited: Boolean,
)

data class ExplorePermissionModel(
    val isSomePermissionRejected: Boolean? = null,
    val isAllPermissionGranted: Boolean = false,
    val isLocationPermissionGranted: Boolean = false,
    val isCameraPermissionGranted: Boolean = false,
)
