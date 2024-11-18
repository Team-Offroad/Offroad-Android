package com.teamoffroad.feature.explore.domain.model

data class Quest(
    val questName: String,
    val description: String,
    val requirement: String,
    val reward: String,
    val cursorId: Int,
    val progress: QuestProgressModel,
) {
    data class QuestProgressModel(
        val currentCount: Int,
        val totalCount: Int,
        val isCompleted: Boolean = currentCount >= totalCount,
    )
}
