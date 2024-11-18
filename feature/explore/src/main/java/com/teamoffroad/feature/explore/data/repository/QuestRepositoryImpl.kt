package com.teamoffroad.feature.explore.data.repository

import com.teamoffroad.feature.explore.data.mapper.toDomain
import com.teamoffroad.feature.explore.data.remote.service.QuestService
import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.domain.repository.QuestRepository
import javax.inject.Inject

class QuestRepositoryImpl @Inject constructor(
    private val questService: QuestService,
) : QuestRepository {

    override suspend fun fetchQuests(isActive: Boolean, cursor: Int, size: Int): List<Quest> {
        return questService.getQuests(isActive, cursor, size).data?.questList?.map { it.toDomain() } ?: emptyList()
    }
}
