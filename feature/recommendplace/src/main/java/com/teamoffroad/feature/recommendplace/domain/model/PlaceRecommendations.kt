package com.teamoffroad.feature.recommendplace.domain.model

data class PlaceRecommendations(
    val recommendations: List<Recommendations>
) {
    data class Recommendations(
        val id: Int,
        val recommendationType: String,
        val name: String,
        val address: String,
        val shortIntroduction: String,
        val placeCategory: String,
        val placeArea: String,
        val latitude: Double,
        val longitude: Double,
        val categoryImageUrl: String
    )
}
