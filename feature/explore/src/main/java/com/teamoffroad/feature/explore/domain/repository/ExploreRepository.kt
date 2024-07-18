package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.model.ExploreQrResult
import com.teamoffroad.feature.explore.domain.model.Place

interface ExploreRepository {

    suspend fun getPlaces(latitude: Double, longitude: Double): List<Place>

    suspend fun postExploreAuth(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreQrResult

    suspend fun postLocationAuth(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult
}
