package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.GainedEmblems
import com.teamoffroad.feature.mypage.domain.repository.GainedEmblemsRepository

class UserEmblemsUseCase(
    private val gainedEmblemsRepository: GainedEmblemsRepository
) {
    suspend operator fun invoke(): Result<List<GainedEmblems>?> {
        return gainedEmblemsRepository.getGainedEmblems()
    }
}