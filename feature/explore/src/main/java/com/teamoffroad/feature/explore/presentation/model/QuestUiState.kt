package com.teamoffroad.feature.explore.presentation.model

data class QuestUiState(
    val totalQuests: List<QuestModel> = emptyList(),
    val proceedingQuests: List<QuestModel> = emptyList(),
    val isProceedingQuest: Boolean = true,
    val isLoading: Boolean = true,
    val isAdditionalLoading: Boolean = false,
    val isLoadable: Pair<Boolean, Boolean> = Pair(true, true),
    val isError: Boolean = false,
)
