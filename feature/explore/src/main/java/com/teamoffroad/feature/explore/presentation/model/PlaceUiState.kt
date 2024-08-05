package com.teamoffroad.feature.explore.presentation.model

data class PlaceUiState(
    val places: List<ExplorePlaceModel> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false,
)
