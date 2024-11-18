package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult

interface UserRepository {

    suspend fun saveLocationAuth(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult
}
