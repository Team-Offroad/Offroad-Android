package com.teamoffroad.feature.diary.domain.model

data class MemoryLightSetting(
    val initialPage: Int,
    val pageCount: Int,
    val memoryLight: List<MemoryLight>,
)