package com.teamoffroad.feature.explore.domain.model

data class Quest(
    val questName: String,
    val description: String,
    val currentCount: Int,
    val totalCount: Int,
    val requirement: String,
    val reward: String,
    val isCompleted: Boolean = currentCount >= totalCount,
)
