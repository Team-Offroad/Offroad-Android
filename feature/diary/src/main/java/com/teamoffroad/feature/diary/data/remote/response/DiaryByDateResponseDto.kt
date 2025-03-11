package com.teamoffroad.feature.diary.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryByDateResponseDto(
    @SerialName("targetDiary")
    val targetDiary: MemoryLightDto,
    @SerialName("previousDiaries")
    val previousDiaries: List<MemoryLightDto>,
    @SerialName("nextDiaries")
    val nextDiaries: List<MemoryLightDto>,
)

@Serializable
data class MemoryLightDto(
    @SerialName("id")
    val id: Int,
    @SerialName("dailyRecommend")
    val dailyRecommend: String,
    @SerialName("content")
    val content: String,
    @SerialName("year")
    val year: Int,
    @SerialName("month")
    val month: Int,
    @SerialName("day")
    val day: Int,
    @SerialName("summation")
    val summation: String,
    @SerialName("hexCodes")
    val hexCodes: List<HexCodeDto>
)