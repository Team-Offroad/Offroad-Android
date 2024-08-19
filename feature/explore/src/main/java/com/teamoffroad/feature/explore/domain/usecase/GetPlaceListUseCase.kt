package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.domain.repository.PlaceRepository

class GetPlaceListUseCase(
    private val placeRepository: PlaceRepository,
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): List<Place> {
        return placeRepository.fetchPlaces(latitude, longitude)
    }
}