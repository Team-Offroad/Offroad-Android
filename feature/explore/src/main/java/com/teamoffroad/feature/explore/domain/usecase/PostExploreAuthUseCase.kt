package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.ExploreResult
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository

class PostExploreAuthUseCase(
    private val exploreRepository: ExploreRepository,
) {

    suspend operator fun invoke(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreResult {
        return exploreRepository.postExploreAuth(placeId, qrCode, latitude, longitude)
    }
}
