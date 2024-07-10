package com.teamoffroad.feature.explore.presentation.model

data class ExploreUiState(
    val locationModel: LocationModel = LocationModel(),
    val loading: Boolean = true,
    val error: Boolean = false,
)
