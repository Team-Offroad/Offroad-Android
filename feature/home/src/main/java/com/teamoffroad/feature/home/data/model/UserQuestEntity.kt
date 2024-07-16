package com.teamoffroad.feature.home.data.model

data class UserQuestEntity(
    val recentEntity: RecentEntity,
    val almostEntity: AlmostEntity
) {
    data class RecentEntity(
        val questName: String,
        val progress: Int,
        val completeCondition: Int
    )

    data class AlmostEntity(
        val questName: String,
        val progress: Int,
        val completeCondition: Int
    )
}