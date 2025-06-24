package com.teamoffroad.feature.explore.domain.model

import com.naver.maps.geometry.LatLng

data class MapPosition(
    val latitude: Double,
    val longitude: Double,
) {
    companion object {
        fun MapPosition.toLatLng(): LatLng = LatLng(latitude, longitude)
    }
}
