package com.teamoffroad.feature.explore.presentation.model

data class ExploreUiState(
    val locationModel: LocationModel = LocationModel(),
    val places: List<ExplorePlaceModel> = emptyList(),
    val authResultType: ExploreAuthState = ExploreAuthState.None,
    val selectedPlace: ExplorePlaceModel? = null,
    val loading: Boolean = true,
    val permissionModel: ExplorePermissionModel = ExplorePermissionModel(),
    val isUpdatePlacesFailed: Boolean = false,
)
