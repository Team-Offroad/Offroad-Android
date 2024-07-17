package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.ExploreResult
import com.teamoffroad.feature.explore.domain.model.Place

interface ExploreRepository {

    suspend fun getPlaces(latitude: Double, longitude: Double): List<Place>

    suspend fun postExploreAuth(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreResult
}
