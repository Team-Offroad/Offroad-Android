package com.teamoffroad.feature.explore.presentation.model

data class QuestUiState(
    val totalQuests: List<QuestModel> = emptyList(),
    val proceedingQuests: List<QuestModel> = emptyList(),
    val isProceedingToggle: Boolean = false,
    val loading: Boolean = true,
    val error: Boolean = false,
)
