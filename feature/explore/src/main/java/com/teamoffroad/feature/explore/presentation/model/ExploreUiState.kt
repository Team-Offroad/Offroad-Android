package com.teamoffroad.feature.explore.presentation.model

data class ExploreUiState(
    val locationModel: LocationModel = LocationModel(),
    val loading: Boolean = true,
    val isAlreadyHavePermission: Boolean = false,
    val isPermissionRejected: Boolean = false,
)
