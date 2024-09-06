package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestsResponseDto(
    @SerialName("questList")
    val questList: List<QuestResponseDto>,
)
