package com.teamoffroad.feature.diary.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryTutorialCheckedResponseDto(
    @SerialName("value")
    val value: Boolean,
)
