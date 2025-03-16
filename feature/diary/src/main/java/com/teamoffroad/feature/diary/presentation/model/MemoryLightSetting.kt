package com.teamoffroad.feature.diary.presentation.model

import com.teamoffroad.feature.diary.domain.model.MemoryLight

data class MemoryLightSetting(
    val initialPage: Int,
    val pageCount: Int,
    val memoryLight: List<MemoryLight>,
)
