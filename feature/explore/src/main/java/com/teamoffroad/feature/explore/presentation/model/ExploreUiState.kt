package com.teamoffroad.feature.explore.presentation.model

data class ExploreUiState(
    val locationModel: LocationModel = LocationModel(),
    val places: List<PlaceModel> = emptyList(),
    val authResultType: ExploreCameraUiState = ExploreCameraUiState.None,
    val selectedPlace: PlaceModel? = null,
    val loading: Boolean = true,
    val isSomePermissionRejected: Boolean? = null,
    val isAllPermissionGranted: Boolean = false,
    val isLocationPermissionGranted: Boolean = false,
    val isCameraPermissionGranted: Boolean = false,
    val isUpdatePlacesFailed: Boolean = false,
) {

    fun getExploreCameraUiState(errorString: String): ExploreCameraUiState {
        return when (errorString) {
            ExploreCameraUiState.LocationError.toString() -> ExploreCameraUiState.LocationError
            ExploreCameraUiState.CodeError.toString() -> ExploreCameraUiState.CodeError
            ExploreCameraUiState.EtcError.toString() -> ExploreCameraUiState.EtcError
            ExploreCameraUiState.Success.toString() -> ExploreCameraUiState.Success
            else -> ExploreCameraUiState.None
        }
    }
}
