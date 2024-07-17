package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.repository.ExploreRepository

class PostExploreAuthUseCase(
    private val exploreRepository: ExploreRepository,
) {

    suspend operator fun invoke(placeId: Long, qrCode: String, latitude: Double, longitude: Double): Boolean {
        return exploreRepository.postExploreAuth(placeId, qrCode, latitude, longitude)
    }
}
