package com.teamoffroad.feature.recommendplace.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceRecommendationsOrderChatRequestDto(
    @SerialName("content")
    val content: String
)
