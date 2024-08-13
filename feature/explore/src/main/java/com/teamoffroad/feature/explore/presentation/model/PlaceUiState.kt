package com.teamoffroad.feature.explore.presentation.model

data class PlaceUiState(
    val places: List<FakePlaceModel> = emptyList(),
    val loading: Boolean = true,
    val error: Boolean = false,
)
