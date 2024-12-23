package com.teamoffroad.feature.explore.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExploreLocationAuthResponseDto(
    @SerialName("isValidPosition")
    val isValidPosition: Boolean,
    @SerialName("isFirstVisitToday")
    val isFirstVisitToday: Boolean,
    @SerialName("successCharacterImageUrl")
    val successCharacterImageUrl: String,
    @SerialName("completeQuestList")
    val completeQuestList: List<QuestResponseDto>?,
) {
    @Serializable
    data class QuestResponseDto(
        @SerialName("name")
        val name: String,
    )
}
