package com.teamoffroad.feature.explore.presentation.model

data class PlaceUiState(
    val visitedPlaces: List<PlaceModel> = emptyList(),
    val unvisitedPlaces: List<PlaceModel> = emptyList(),
    val isLoading: Boolean = true,
    val isAdditionalLoading: Boolean = false,
    val isLoadable: Boolean = true,
    val isError: Boolean = false,
)
