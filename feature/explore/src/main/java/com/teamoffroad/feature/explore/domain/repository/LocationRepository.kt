package com.teamoffroad.feature.explore.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(): Flow<Pair<Double, Double>>
    suspend fun saveLocation(latitude: Double, longitude: Double)
}
