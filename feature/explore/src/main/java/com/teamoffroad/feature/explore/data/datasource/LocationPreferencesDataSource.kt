package com.teamoffroad.feature.explore.data.datasource

import kotlinx.coroutines.flow.Flow

interface LocationPreferencesDataSource {
    val location: Flow<Pair<Double, Double>>

    suspend fun setLocation(latitude: Double, longitude: Double)
}
