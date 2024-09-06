package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExploreLocationAuthResponseDto(
    @SerialName("isValidPosition")
    val isValidPosition: Boolean = false,
    @SerialName("successCharacterImageUrl")
    val successCharacterImageUrl: String = "",
    @SerialName("completeQuestList")
    val completeQuestList: List<QuestResponseDto> = emptyList(),
) {
    @Serializable
    data class QuestResponseDto(
        @SerialName("name")
        val name: Long,
    )
}
