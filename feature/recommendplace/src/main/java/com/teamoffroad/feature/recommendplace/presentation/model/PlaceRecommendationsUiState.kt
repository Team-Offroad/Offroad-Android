package com.teamoffroad.feature.recommendplace.presentation.model

data class PlaceRecommendationsUiState(
    val recommendations: List<RecommendationsUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
) {
    data class RecommendationsUiState(
        val id: Int,
        val recommendationType: String,
        val name: String,
        val address: String,
        val shortIntroduction: String,
        val placeCategory: String,
        val placeArea: String,
        val latitude: Double,
        val longitude: Double,
        val categoryImageUrl: String,
    )
}
