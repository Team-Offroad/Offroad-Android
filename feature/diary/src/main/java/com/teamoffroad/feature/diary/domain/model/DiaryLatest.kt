package com.teamoffroad.feature.diary.domain.model

data class DiaryLatest(
    val targetDiary: MemoryLight,
    val previousDiaries: List<MemoryLight>,
)
