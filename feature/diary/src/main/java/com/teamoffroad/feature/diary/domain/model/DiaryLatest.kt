package com.teamoffroad.feature.diary.domain.model

data class DiaryLatest(
    val latestDiary: MemoryLight,
    val previousDiaries: List<MemoryLight>,
)
