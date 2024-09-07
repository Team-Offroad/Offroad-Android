package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.UserRepository

class SaveUserMarketingInfoUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(): Result<Unit> {
        return userRepository.saveMarketingInfo()
    }
}