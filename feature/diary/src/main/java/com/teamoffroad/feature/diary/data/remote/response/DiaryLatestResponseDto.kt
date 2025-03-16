package com.teamoffroad.feature.diary.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryLatestResponseDto(
    @SerialName("latestDiary")
    val latestDiary: MemoryLightDto,
    @SerialName("previousDiaries")
    val previousDiaries: List<MemoryLightDto>,
)
