package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.repository.EmblemRepository

class GetEmblemListUseCase(
    private val emblemRepository: EmblemRepository,
) {
    suspend fun getEmblems(token: String): List<Emblem> {
        return emblemRepository.getEmblems(token)
    }

    suspend fun getUserQuests(token: String): UserQuests {
        return emblemRepository.getUserQuests(token)
    }
}