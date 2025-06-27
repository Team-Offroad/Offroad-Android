package com.teamoffroad.feature.explore.domain.model

import com.naver.maps.geometry.LatLng

data class Location(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
) {
    companion object {
        fun Location.toLatLng(): LatLng = LatLng(latitude, longitude)
    }
}
