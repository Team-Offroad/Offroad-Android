package com.teamoffroad.feature.explore.presentation.model

data class PlaceUiState(
    val visitedPlaces: List<PlaceModel> = emptyList(),
    val unvisitedPlaces: List<PlaceModel> = emptyList(),
    val loading: Boolean = true,
    val isAdditionalLoading: Boolean = false,
    val isLoadable: Boolean = true,
    val error: Boolean = false,
)
