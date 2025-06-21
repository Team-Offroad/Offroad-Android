package com.teamoffroad.feature.recommendplace.domain.model

data class PlaceRecommendationsOrder(
    val recommendationType: String,
    val region: String,
    val additionalContent: String
)
