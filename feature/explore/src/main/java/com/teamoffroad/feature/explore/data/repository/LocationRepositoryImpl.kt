package com.teamoffroad.feature.explore.data.repository

import com.teamoffroad.feature.explore.data.datasource.LocationPreferencesDataSource
import com.teamoffroad.feature.explore.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val locationPreferencesDataSource: LocationPreferencesDataSource,
) : LocationRepository {
    override fun getLocation(): Flow<Pair<Double, Double>> {
        return locationPreferencesDataSource.location
    }

    override suspend fun saveLocation(latitude: Double, longitude: Double) {
        locationPreferencesDataSource.setLocation(latitude, longitude)
    }
}