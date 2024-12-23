package com.teamoffroad.feature.explore.domain.model

data class ExploreLocationResult(
    val isValidPosition: Boolean,
    val isFirstVisitToday: Boolean,
    val successCharacterImageUrl: String,
    val completeQuests: List<String>,
)
