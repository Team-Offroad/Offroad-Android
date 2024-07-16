package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.repository.EmblemRepository

class GetEmblemListUseCase(
    private val emblemRepository: EmblemRepository,
) {
    suspend fun getEmblems(): List<Emblem> {
        return emblemRepository.getEmblems()
    }

    suspend operator fun invoke(emblemCode: String) {
        return emblemRepository.patchEmblem(emblemCode, )
    }

    suspend fun getUserQuests(): UserQuests {
        return emblemRepository.getUserQuests()
    }
}