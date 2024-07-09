package com.teamoffroad.feature.explore.presentation.model

data class UiState(
    val locationModel: LocationModel? = null,
    val loading: Boolean = true,
    val error: Boolean = false,
)
