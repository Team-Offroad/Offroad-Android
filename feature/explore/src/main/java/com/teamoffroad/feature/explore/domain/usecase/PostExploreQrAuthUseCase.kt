package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.ExploreQrResult
import com.teamoffroad.feature.explore.domain.repository.ExploreRepository

class PostExploreQrAuthUseCase(
    private val exploreRepository: ExploreRepository,
) {

    suspend operator fun invoke(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreQrResult {
        return exploreRepository.postExploreAuth(placeId, qrCode, latitude, longitude)
    }
}
