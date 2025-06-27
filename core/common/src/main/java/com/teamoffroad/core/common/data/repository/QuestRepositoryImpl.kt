package com.teamoffroad.core.common.data.repository

import com.teamoffroad.core.common.domain.preferences.QuestPreferences
import com.teamoffroad.core.common.domain.repository.QuestRepository
import javax.inject.Inject

class QuestRepositoryImpl
    @Inject
    constructor(
        private val questPreferences: QuestPreferences,
    ) : QuestRepository {
        override suspend fun getCategory(): String? = questPreferences.getCategory()

        override suspend fun getCompleteQuests(): List<String> = questPreferences.getCompleteQuests()

        override suspend fun saveCategory(category: String) {
            questPreferences.saveCategory(category)
        }

        override suspend fun saveCompleteQuests(quests: List<String>) {
            questPreferences.saveCompleteQuests(quests)
        }

        override suspend fun clearCategory() {
            questPreferences.clearCategory()
        }

        override suspend fun clearCompleteQuests() {
            questPreferences.clearCompleteQuests()
        }
    }
