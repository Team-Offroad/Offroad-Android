package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.Place
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository

class GetPlaceListUseCase(
    private val exploreRepository: ExploreRepository,
) {

    suspend operator fun invoke(latitude: Double, longitude: Double): List<Place> {
        return exploreRepository.getPlaces(latitude, longitude)
    }
}