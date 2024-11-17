package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.repository.FcmTokenRepository

class PostFcmTokenUseCase(
    private val fcmTokenRepository: FcmTokenRepository
) {
    suspend operator fun invoke(fcmToken: String) {
        return fcmTokenRepository.postFcmToken(fcmToken)
    }
}