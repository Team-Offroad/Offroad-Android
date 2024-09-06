package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository

class UserMarketingInfoUseCase(
    private val userRepository: MyPageUserRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return userRepository.patchMarketingInfo()
    }
}