package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow

class GetPreviousLocationUseCase(
    private val locationRepository: LocationRepository,
) {
    operator fun invoke(): Flow<Pair<Double, Double>> {
        return locationRepository.getLocation()
    }
}
