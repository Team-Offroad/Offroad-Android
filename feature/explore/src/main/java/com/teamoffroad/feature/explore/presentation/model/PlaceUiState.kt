package com.teamoffroad.feature.explore.presentation.model

data class PlaceUiState(
    val places: List<ExplorePlaceModel> = emptyList(),
    val authResultType: ExploreResultState = ExploreResultState.None,
    val selectedPlace: ExplorePlaceModel? = null,
    val loading: Boolean = true,
)
