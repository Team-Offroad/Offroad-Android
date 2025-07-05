package com.teamoffroad.feature.recommendplace.presentation.model

data class PlaceRecommendationsFixedPhraseUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val content: String = "",
)
