package com.teamoffroad.core.common.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.teamoffroad.core.common.domain.preferences.QuestPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

class QuestPreferencesImpl
    @Inject
    constructor(
        @Named(QUEST_PREFERENCES_NAME) private val dataStore: DataStore<Preferences>,
    ) : QuestPreferences {
        object PreferencesKey {
            val CATEGORY = stringPreferencesKey("CATEGORY")
            val COMPLETE_QUESTS = stringSetPreferencesKey("COMPLETE_QUESTS")
        }

        override val category: Flow<String> =
            dataStore.data.map { preferences ->
                preferences[PreferencesKey.CATEGORY] ?: ""
            }

        override val completeQuests: Flow<List<String>> =
            dataStore.data.map { preferences ->
                preferences[PreferencesKey.COMPLETE_QUESTS]?.toList() ?: emptyList()
            }

        override suspend fun getCategory(): String {
            val value = dataStore.data.map { it[PreferencesKey.CATEGORY] ?: "" }.first()
            clearCategory()
            return value
        }

        override suspend fun getCompleteQuests(): List<String> {
            val value =
                dataStore.data
                    .map {
                        it[PreferencesKey.COMPLETE_QUESTS]?.toList() ?: emptyList()
                    }.first()
            clearCompleteQuests()
            return value
        }

        override suspend fun saveCategory(category: String) {
            dataStore.edit { it[PreferencesKey.CATEGORY] = category }
        }

        override suspend fun saveCompleteQuests(quests: List<String>) {
            dataStore.edit {
                it[PreferencesKey.COMPLETE_QUESTS] = quests.toSet()
            }
        }

        override suspend fun clearCategory() {
            dataStore.edit { it.remove(PreferencesKey.CATEGORY) }
        }

        override suspend fun clearCompleteQuests() {
            dataStore.edit { it.remove(PreferencesKey.COMPLETE_QUESTS) }
        }

        companion object {
            const val QUEST_PREFERENCES_NAME = "QUEST_DATA_STORE"
        }
    }
