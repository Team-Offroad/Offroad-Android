package com.teamoffroad.feature.diary.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryMonthlyHexResponseDto(
    @SerialName("dailyHexCodes")
    val dailyHexCodes: Map<String, List<HexCodeDto>>,
    @SerialName("firstDiaryDay")
    val firstDiaryDay: Int?,
)

@Serializable
data class HexCodeDto(
    @SerialName("small")
    val small: String,
    @SerialName("large")
    val large: String,
)
