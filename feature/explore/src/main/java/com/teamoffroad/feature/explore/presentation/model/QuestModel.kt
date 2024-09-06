package com.teamoffroad.feature.explore.presentation.model

data class QuestModel(
    val questName: String,
    val description: String,
    val requirement: String,
    val reward: String,
    val questProgressModel: QuestProgressModel,
) {
    data class QuestProgressModel(
        val progressCount: Int,
        val totalCount: Int,
        val isCompleted: Boolean,
    )
}
