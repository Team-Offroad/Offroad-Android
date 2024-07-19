package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository

class PostExploreLocationAuthUseCase(
    private val exploreRepository: ExploreRepository,
) {

    suspend operator fun invoke(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult {
        return exploreRepository.postLocationAuth(placeId, latitude, longitude)
    }
}
