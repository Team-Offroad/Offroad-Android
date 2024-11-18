package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetPreviousLocationUseCase
import com.teamoffroad.feature.explore.domain.usecase.PostExploreLocationAuthUseCase
import com.teamoffroad.feature.explore.domain.usecase.SavePreviousLocationUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.ExploreAuthState
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.explore.presentation.model.PlaceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getPlaceListUseCase: GetPlaceListUseCase,
    private val postExploreLocationAuthUseCase: PostExploreLocationAuthUseCase,
    private val getPreviousLocationUseCase: GetPreviousLocationUseCase,
    private val savePreviousLocationUseCase: SavePreviousLocationUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                getPreviousLocationUseCase()
                    .firstOrNull()
                    ?.let { (latitude, longitude) ->
                        updateLocation(latitude, longitude)
                    }
            }
        }
    }

    fun updatePermission(
        isLocationPermissionGranted: Boolean,
    ) {
        _uiState.value = uiState.value.copy(
            isLocationPermissionGranted = isLocationPermissionGranted,
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
        viewModelScope.launch {
            savePreviousLocationUseCase(latitude, longitude)
        }
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean) {
        if (!isUserTrackingEnabled) updatePlaces()
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
                getPlaceListUseCase(latitude, longitude, LOAD_PLACES_LIMIT, true).map { it.toUi() }
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
            selectedPlace = place,
        )
    }

    fun updateExploreAuthState(errorType: ExploreAuthState) {
        _uiState.value = uiState.value.copy(
            authResultType = errorType,
        )
    }

    fun updateExploreResult(placeId: Long, latitude: Double, longitude: Double, category: PlaceCategory) {
        viewModelScope.launch {
            runCatching {
                postExploreLocationAuthUseCase(placeId, latitude, longitude)
            }.onSuccess { exploreResult ->
                if (exploreResult.isValidPosition) {
                    updateExploreAuthState(
                        ExploreAuthState.Success(
                            category,
                            exploreResult.successCharacterImageUrl,
                            exploreResult.completeQuests,
                        )
                    )
                } else {
                    updateExploreAuthState(ExploreAuthState.LocationError)
                }
            }.onFailure {
                updateExploreAuthState(ExploreAuthState.EtcError)
            }
        }
    }

    companion object {
        private const val LOAD_PLACES_LIMIT = 100
    }
}
