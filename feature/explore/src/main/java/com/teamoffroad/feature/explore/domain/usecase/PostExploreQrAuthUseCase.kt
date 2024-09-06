package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.ExploreQrResult
import com.teamoffroad.feature.explore.domain.repository.UserRepository

class PostExploreQrAuthUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(placeId: Long, qrCode: String, latitude: Double, longitude: Double): ExploreQrResult {
        return userRepository.saveExploreAuth(placeId, qrCode, latitude, longitude)
    }
}
