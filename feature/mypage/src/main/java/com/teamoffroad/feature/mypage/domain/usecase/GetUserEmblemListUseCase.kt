package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.GainedEmblem
import com.teamoffroad.feature.mypage.domain.repository.EmblemRepository

class GetUserEmblemListUseCase(
    private val emblemRepository: EmblemRepository,
) {
    suspend operator fun invoke(): Result<List<GainedEmblem>?> {
        return emblemRepository.getGainedEmblems()
    }
}