package com.teamoffroad.feature.explore.domain.repository

import com.teamoffroad.feature.explore.domain.model.Quest

interface QuestRepository {

    suspend fun fetchQuests(isActive: Boolean, cursor: Int, size: Int): List<Quest>
}
