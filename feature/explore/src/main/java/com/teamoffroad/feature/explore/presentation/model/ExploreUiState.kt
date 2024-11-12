package com.teamoffroad.feature.explore.presentation.model

data class ExploreUiState(
    val locationModel: LocationModel = LocationModel(),
    val places: List<PlaceModel> = emptyList(),
    val authResultType: ExploreAuthState = ExploreAuthState.None,
    val selectedPlace: PlaceModel? = null,
    val loading: Boolean = true,
    val isLocationPermissionGranted: Boolean? = null,
    val isUpdatePlacesFailed: Boolean = false,
)
