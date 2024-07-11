package com.teamoffroad.feature.explore.presentation.model

import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.LocationTrackingMode

data class LocationModel(
    val location: LatLng = LatLng(
        DEFAULT_LOCATION_LATITUDE.toDouble(),
        DEFAULT_LOCATION_LONGITUDE.toDouble(),
    ),
    val isAlreadyHavePermission: Boolean = false,
    val isPermissionRejected: Boolean = false,
    val isUserTrackingEnabled: Boolean = true,
    val locationTrackingMode: LocationTrackingMode = when (isUserTrackingEnabled) {
        true -> LocationTrackingMode.Follow
        false -> LocationTrackingMode.NoFollow
    },
) {

    fun updateLocation(latitude: Float, longitude: Float): LocationModel {
        return copy(
            location = LatLng(
                latitude.toDouble(),
                longitude.toDouble(),
            ),
        )
    }

    fun updatePermission(isAlreadyHavePermission: Boolean, isPermissionRejected: Boolean): LocationModel {
        return copy(
            isAlreadyHavePermission = isAlreadyHavePermission,
            isPermissionRejected = isPermissionRejected,
        )
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean): LocationModel {
        return copy(
            isUserTrackingEnabled = !isUserTrackingEnabled,
        )
    }

    companion object {
        private const val DEFAULT_LOCATION_LATITUDE = 37.588764f
        private const val DEFAULT_LOCATION_LONGITUDE = 127.05879f
    }
}
