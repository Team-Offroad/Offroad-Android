package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.repository.LocationRepository

class SavePreviousLocationUseCase(
    private val locationRepository: LocationRepository,
) {
    suspend operator fun invoke(latitude: Double, longitude: Double) {
        locationRepository.saveLocation(latitude, longitude)
    }
}
