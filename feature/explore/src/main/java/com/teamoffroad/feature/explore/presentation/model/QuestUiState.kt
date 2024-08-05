package com.teamoffroad.feature.explore.presentation.model

data class QuestUiState(
    val quests: List<FakeQuestModel> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false,
)
