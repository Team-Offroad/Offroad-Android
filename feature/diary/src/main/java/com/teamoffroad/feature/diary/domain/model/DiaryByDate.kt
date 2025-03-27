package com.teamoffroad.feature.diary.domain.model

data class DiaryByDate(
    val targetDiary: MemoryLight,
    val previousDiaries: List<MemoryLight>,
    val nextDiaries: List<MemoryLight>,
)
