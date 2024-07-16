package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.repository.EmblemRepository

class GetEmblemListUseCase(
    private val emblemRepository: EmblemRepository,
) {
    suspend operator fun invoke(token: String): List<Emblem> {
        return emblemRepository.getEmblems(token)
    }

    suspend operator fun invoke(emblemCode: String, token: String) {
        return emblemRepository.patchEmblem(emblemCode, token)
    }
}