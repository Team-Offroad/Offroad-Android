package com.teamoffroad.feature.recommendplace.data.model

data class PlaceRecommendationsEntity(
    val recommendations: List<RecommendationsEntity>
) {
    data class RecommendationsEntity(
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
