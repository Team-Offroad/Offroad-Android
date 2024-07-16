package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlaceListUseCase: GetPlaceListUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    fun updatePermission(
        isSomePermissionRejected: Boolean?,
        isLocationPermissionGranted: Boolean,
        isCameraPermissionGranted: Boolean,
    ) {
        _uiState.value = uiState.value.copy(
            isSomePermissionRejected = isSomePermissionRejected,
            isAllPermissionGranted = isLocationPermissionGranted && isCameraPermissionGranted,
            isLocationPermissionGranted = isLocationPermissionGranted,
            isCameraPermissionGranted = isCameraPermissionGranted,
        )
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _uiState.value = uiState.value.copy(
            locationModel = uiState.value.locationModel.updateLocation(latitude, longitude)
        )
        if (uiState.value.locationModel.isUserMoveFarEnough()) {
            _uiState.value = uiState.value.copy(
                locationModel = uiState.value.locationModel.updatePreviousLocation(LatLng(latitude, longitude)),
            )
            updatePlaces(latitude, longitude)
        }
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean) {
        _uiState.value = uiState.value.copy(
            locationModel = uiState.value.locationModel.updateTrackingToggle(isUserTrackingEnabled)
        )
    }

    fun updatePlaces(
        latitude: Double = uiState.value.locationModel.location.latitude,
        longitude: Double = uiState.value.locationModel.location.longitude,
    ) {
        viewModelScope.launch {
            runCatching {
                getPlaceListUseCase(latitude, longitude).map { it.toUi() }
            }.onSuccess { places ->
                _uiState.value = uiState.value.copy(
                    places = places,
                    loading = false,
                )
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    places = emptyList(),
                    loading = false,
                    isUpdatePlacesFailed = true,
                )

            }
        }
    }

    fun updateSelectedPlace(place: PlaceModel?) {
        _uiState.value = uiState.value.copy(
            selectedPlace = place
        )
    }
}
