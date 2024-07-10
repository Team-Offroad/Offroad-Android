package com.teamoffroad.feature.explore.presentation.model

data class LocationModel(
    val latitude: Float = DEFAULT_LOCATION_LATITUDE,
    val longitude: Float = DEFAULT_LOCATION_LONGITUDE,
    val isAlreadyHavePermission: Boolean = false,
    val isPermissionRejected: Boolean = false,
) {
    fun updateLocation(latitude: Float, longitude: Float): LocationModel {
        return copy(
            latitude = latitude,
            longitude = longitude,
        )
    }

    fun updatePermission(isAlreadyHavePermission: Boolean, isPermissionRejected: Boolean): LocationModel {
        return copy(
            isAlreadyHavePermission = isAlreadyHavePermission,
            isPermissionRejected = isPermissionRejected,
        )
    }

    companion object {
        private const val DEFAULT_LOCATION_LATITUDE = 37.588764f
        private const val DEFAULT_LOCATION_LONGITUDE = 127.05879f
    }
}
