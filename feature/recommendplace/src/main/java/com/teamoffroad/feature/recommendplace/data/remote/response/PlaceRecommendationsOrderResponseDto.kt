package com.teamoffroad.feature.recommendplace.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceRecommendationsOrderResponseDto(
    @SerialName("content")
    val content: String
)