package com.teamoffroad.feature.diary.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryCreateTimeCheckedResponseDto(
    @SerialName("value")
    val value: Boolean,
)
