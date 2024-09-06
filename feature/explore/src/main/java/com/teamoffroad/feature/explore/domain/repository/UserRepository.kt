package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.model.ExploreQrResult

interface UserRepository {

    suspend fun saveExploreAuth(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreQrResult

    suspend fun saveLocationAuth(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult
}
