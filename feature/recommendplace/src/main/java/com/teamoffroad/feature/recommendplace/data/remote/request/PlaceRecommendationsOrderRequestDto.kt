package com.teamoffroad.feature.recommendplace.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class PlaceRecommendationsOrderRequestDto (
    val recommendationType: String,
    val region: String,
    val additionalContent: String,
)