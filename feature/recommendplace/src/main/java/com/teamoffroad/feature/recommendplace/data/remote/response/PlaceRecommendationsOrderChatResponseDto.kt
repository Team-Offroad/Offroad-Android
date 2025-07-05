package com.teamoffroad.feature.recommendplace.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceRecommendationsOrderChatResponseDto(
    @SerialName("content")
    val content: String,

    @SerialName("success")
    val success: Boolean,
)
