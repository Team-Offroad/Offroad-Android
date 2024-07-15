package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.model.Emblems
import com.teamoffroad.feature.home.domain.repository.EmblemRepository

class GetEmblemListUseCase(
    private val emblemRepository: EmblemRepository,
) {
    suspend operator fun invoke(token: String): Emblems {
        return emblemRepository.getEmblems(token)
    }
}