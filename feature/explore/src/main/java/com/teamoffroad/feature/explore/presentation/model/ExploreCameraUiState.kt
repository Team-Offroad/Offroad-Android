package com.teamoffroad.feature.explore.presentation.model

sealed interface ExploreCameraUiState {
    data object None : ExploreCameraUiState
    data object Loading : ExploreCameraUiState
    data object Success : ExploreCameraUiState
    data object LocationError : ExploreCameraUiState
    data object CodeError : ExploreCameraUiState
    data object EtcError : ExploreCameraUiState
}
