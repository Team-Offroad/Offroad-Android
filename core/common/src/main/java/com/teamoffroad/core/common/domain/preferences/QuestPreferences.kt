package com.teamoffroad.core.common.domain.preferences

import kotlinx.coroutines.flow.Flow

interface QuestPreferences {
    val category: Flow<String>

    val completeQuests: Flow<List<String>>

    suspend fun getCategory(): String

    suspend fun getCompleteQuests(): List<String>

    suspend fun saveCategory(category: String)

    suspend fun saveCompleteQuests(quests: List<String>)

    suspend fun clearCategory()

    suspend fun clearCompleteQuests()
}
