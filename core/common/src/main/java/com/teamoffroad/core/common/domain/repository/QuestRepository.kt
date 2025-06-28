package com.teamoffroad.core.common.domain.repository

interface QuestRepository {
    suspend fun getCategory(): String?

    suspend fun getCompleteQuests(): List<String>

    suspend fun saveCategory(category: String)

    suspend fun saveCompleteQuests(quests: List<String>)

    suspend fun clearCategory()

    suspend fun clearCompleteQuests()
}
