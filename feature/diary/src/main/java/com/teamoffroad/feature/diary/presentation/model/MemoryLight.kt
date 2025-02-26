package com.teamoffroad.feature.diary.presentation.model

data class MemoryLight(
    val id: Int,
    val dailyRecommend: String,
    val content: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val summation: String,
    val hexCode: String,
)
