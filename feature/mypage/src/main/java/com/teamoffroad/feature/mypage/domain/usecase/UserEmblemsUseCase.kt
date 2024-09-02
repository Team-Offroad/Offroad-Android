package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import com.teamoffroad.feature.mypage.domain.repository.UserRepository

class UserEmblemsUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<GainedEmblems>?> {
        return userRepository.getGainedEmblems()
    }
}