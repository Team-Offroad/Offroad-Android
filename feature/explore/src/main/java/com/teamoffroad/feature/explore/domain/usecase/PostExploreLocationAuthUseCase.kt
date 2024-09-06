package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.repository.UserRepository

class PostExploreLocationAuthUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(placeId: Long, latitude: Double, longitude: Double): ExploreLocationResult {
        return userRepository.saveLocationAuth(placeId, latitude, longitude)
    }
}
