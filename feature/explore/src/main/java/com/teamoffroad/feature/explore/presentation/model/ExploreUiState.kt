package com.teamoffroad.feature.explore.presentation.model

data class ExploreUiState(
    val locationModel: LocationModel = LocationModel(),
    val places: List<ExplorePlaceModel> = emptyList(),
    val authResultType: ExploreResultState = ExploreResultState.None,
    val selectedPlace: ExplorePlaceModel? = null,
    val loading: Boolean = true,
    val isSomePermissionRejected: Boolean? = null,
    val isAllPermissionGranted: Boolean = false,
    val isLocationPermissionGranted: Boolean = false,
    val isCameraPermissionGranted: Boolean = false,
    val isUpdatePlacesFailed: Boolean = false,
) {

    fun getExploreCameraUiState(errorString: String): ExploreResultState {
        return when (errorString) {
            ExploreResultState.LocationError.toString() -> ExploreResultState.LocationError
            ExploreResultState.CodeError.toString() -> ExploreResultState.CodeError
            ExploreResultState.EtcError.toString() -> ExploreResultState.EtcError
            ExploreResultState.Success.toString() -> ExploreResultState.Success
            else -> ExploreResultState.None
        }
    }
}
