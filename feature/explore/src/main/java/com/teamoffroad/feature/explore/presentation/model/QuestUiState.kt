package com.teamoffroad.feature.explore.presentation.model

import com.teamoffroad.feature.explore.domain.model.Quest

data class QuestUiState(
    val totalQuests: List<Quest> = emptyList(),
    val proceedingQuests: List<Quest> = emptyList(),
    val isProceedingQuest: Boolean = true,
    val isLoading: Boolean = true,
    val isAdditionalLoading: Boolean = false,
    val isLoadable: Pair<Boolean, Boolean> = Pair(true, true),
    val isError: Boolean = false,
)
