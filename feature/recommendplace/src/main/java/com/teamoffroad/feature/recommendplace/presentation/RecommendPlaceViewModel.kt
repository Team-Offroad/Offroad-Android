package com.teamoffroad.feature.recommendplace.presentation


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import com.teamoffroad.feature.explore.domain.usecase.GetMapPlaceListUseCase
import com.teamoffroad.feature.explore.domain.usecase.GetPreviousLocationUseCase
import com.teamoffroad.feature.explore.domain.usecase.SavePreviousLocationUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.PlaceCategory
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendPlaceViewModel @Inject constructor(
    private val getMapPlaceListUseCase: GetMapPlaceListUseCase,
    private val savePreviousLocationUseCase: SavePreviousLocationUseCase,
    private val placeRecommendationsRepository: PlaceRecommendationsRepository,
    private val getPreviousLocationUseCase: GetPreviousLocationUseCase,
) : ViewModel() {
    private val _placeRecommendationsUiState = MutableStateFlow(PlaceRecommendationsUiState())
    val placeRecommendationsUiState = _placeRecommendationsUiState.asStateFlow()

    fun getPlaceRecommendations() {
        if (placeRecommendationsUiState.value.isAdditionalLoading || placeRecommendationsUiState.value.isLoadable.not()) return

        viewModelScope.launch {
            runCatching {
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isAdditionalLoading = true,
                    isError = false,
                )
                placeRecommendationsRepository.fetchPlaceRecommendations()
            }.onSuccess { place ->
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isError = false,
                    isAdditionalLoading = false,
                    isLoadable = place.recommendations.isEmpty().not(),
                    isLoading = false,
                    recommendations = place.recommendations.map {
                        PlaceRecommendationsUiState.RecommendationsUiState(
                            id = it.id,
                            recommendationType = it.recommendationType,
                            name = it.name,
                            address = it.address,
                            shortIntroduction = it.shortIntroduction,
                            placeCategory = PlaceCategory.entries.find { cat -> cat.name == it.placeCategory }
                                ?: PlaceCategory.NONE,
                            placeArea = it.placeArea,
                            latitude = it.latitude,
                            longitude = it.longitude,
                            categoryImageUrl = it.categoryImageUrl,
                            location = LatLng(it.latitude, it.longitude)
                        )
                    }
                )
            }.onFailure { t ->
                // 실제 부분
//                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
//                    isLoading = false,
//                    isAdditionalLoading = false,
//                    isError = true,
//                    errorMessage = t.message.toString(),
//                    recommendations = emptyList()
//                )

                // 테스트용 추천 항목 추가 -> 추후 삭제할 부분
                val testRecommendation = PlaceRecommendationsUiState.RecommendationsUiState(
                    id = 1,
                    recommendationType = "RESTAURANT,CAFE",
                    name = "테스트 장소",
                    address = "서울시 강남구 테스트로 123",
                    shortIntroduction = "테스트 장소입니다.",
                    placeCategory = PlaceCategory.RESTAURANT,
                    placeArea = "SEOUL",
                    latitude = 37.123456,
                    longitude = 127.123456,
                    categoryImageUrl = "https://test.com/test.jpg",
                    location = LatLng(37.123456, 127.123456)
                )
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isError = false,
                    isAdditionalLoading = false,
                    isLoading = false,
                    recommendations = emptyList<PlaceRecommendationsUiState.RecommendationsUiState>().plus(
                        testRecommendation
                    )
                )
            }
        }
    }

    init {
        viewModelScope.launch {
            runCatching {
                getPreviousLocationUseCase()
                    .firstOrNull()
                    ?.let { (latitude, longitude) ->
                        updateLocation(latitude, longitude)
                        updateCameraState(latitude, longitude)
                    } ?: updateLocation(
                    placeRecommendationsUiState.value.locationModel.location.latitude,
                    placeRecommendationsUiState.value.locationModel.location.longitude
                )
            }
        }
    }

    private fun updateCameraState(latitude: Double, longitude: Double) {
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            locationModel = placeRecommendationsUiState.value.locationModel.updateCameraPositionState(
                latitude,
                longitude
            )
        )
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            locationModel = placeRecommendationsUiState.value.locationModel.updateLocation(
                latitude,
                longitude
            )
        )
        if (placeRecommendationsUiState.value.locationModel.isUserMoveFarEnough() || placeRecommendationsUiState.value.recommendations.isEmpty()) {
            _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
                locationModel = placeRecommendationsUiState.value.locationModel.updatePreviousLocation(
                    LatLng(latitude, longitude)
                ),
            )
            updatePlaces(latitude, longitude)
        }
        viewModelScope.launch {
            savePreviousLocationUseCase(latitude, longitude)
        }
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean) {
        if (!isUserTrackingEnabled) updatePlaces()
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            locationModel = placeRecommendationsUiState.value.locationModel.updateTrackingToggle(
                isUserTrackingEnabled
            )
        )
    }

    fun updatePlaces(
        latitude: Double = placeRecommendationsUiState.value.locationModel.location.latitude,
        longitude: Double = placeRecommendationsUiState.value.locationModel.location.longitude,
    ) {
        viewModelScope.launch {
            runCatching {
                getMapPlaceListUseCase(latitude, longitude, LOAD_PLACES_LIMIT).map { it.toUi() }
            }.onSuccess { places ->
                _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
//                    recommendations = places,
                    isLoadable = false,
                )
            }.onFailure {
                _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
                    recommendations = emptyList(),
                    isLoadable = false,
                )
            }
        }
    }

    fun updateSelectedPlace(place: PlaceRecommendationsUiState.RecommendationsUiState?) {
        _placeRecommendationsUiState.value = placeRecommendationsUiState.value.copy(
            selectedPlace = place,
        )
    }

    companion object {
        private const val LOAD_PLACES_LIMIT = 100
    }

}