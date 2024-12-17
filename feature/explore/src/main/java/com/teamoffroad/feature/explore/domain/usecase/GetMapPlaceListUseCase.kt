package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.domain.repository.PlaceRepository

class GetMapPlaceListUseCase(
    private val placeRepository: PlaceRepository,
) {

    suspend operator fun invoke(latitude: Double, longitude: Double, limit: Int): List<Place> {
        return placeRepository.fetchMapPlaces(latitude, longitude, limit)
    }
}