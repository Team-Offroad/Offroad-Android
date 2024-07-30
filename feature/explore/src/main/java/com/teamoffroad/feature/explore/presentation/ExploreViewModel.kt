package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreLocationAuthUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
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
    private val postExploreLocationAuthUseCase: PostExploreLocationAuthUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    private val _category = MutableStateFlow("")
    val category = _category.asStateFlow()

    private val _successImageUrl = MutableStateFlow("")
    val successImageUrl = _successImageUrl.asStateFlow()

    fun updateCategory(category: String) {
        _category.value = category
    }

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

    fun isValidDistance(place: PlaceModel, location: LatLng): Boolean {
        return when (place.placeCategory) {
            PlaceCategory.CAFFE -> place.location.distanceTo(location) <= 45
            PlaceCategory.PARK -> place.location.distanceTo(location) <= 120
            PlaceCategory.RESTAURANT -> place.location.distanceTo(location) <= 45
            PlaceCategory.CULTURE -> place.location.distanceTo(location) <= 45
            PlaceCategory.SPORT -> place.location.distanceTo(location) <= 120
            else -> false
        }
    }

    fun updateExploreCameraUiState(errorType: ExploreCameraUiState) {
        _uiState.value = uiState.value.copy(
            errorType = errorType
        )
    }

    fun postExploreResult(placeId: Long, latitude: Double, longitude: Double) {
        viewModelScope.launch {
            runCatching {
                postExploreLocationAuthUseCase(placeId, latitude, longitude)
            }.onSuccess { exploreResult ->
                if (exploreResult.isValidPosition) {
                    updateExploreCameraUiState(ExploreCameraUiState.Success)
                    _successImageUrl.value = exploreResult.successCharacterImageUrl
                } else {
                    updateExploreCameraUiState(ExploreCameraUiState.LocationError)
                }
            }.onFailure {
                updateExploreCameraUiState(ExploreCameraUiState.EtcError)
            }
        }
    }
}
