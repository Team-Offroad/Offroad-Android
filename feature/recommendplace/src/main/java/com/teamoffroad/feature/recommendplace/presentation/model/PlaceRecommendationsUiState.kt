package com.teamoffroad.feature.recommendplace.presentation.model

import com.teamoffroad.feature.explore.presentation.model.PlaceCategory

data class PlaceRecommendationsUiState(
    val recommendations: List<RecommendationsUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isAdditionalLoading: Boolean = false,
    val isLoadable: Boolean = true,
    val isError: Boolean = false,
) {
    data class RecommendationsUiState(
        val id: Int,
        val recommendationType: String,
        val name: String,
        val address: String,
        val shortIntroduction: String,
        val placeCategory: PlaceCategory,
        val placeArea: String,
        val latitude: Double,
        val longitude: Double,
        val categoryImageUrl: String,
    )
}
