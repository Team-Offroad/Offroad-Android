package com.teamoffroad.feature.recommendplace.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceRecommendationsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendPlaceViewModel @Inject constructor(
    private val placeRecommendationsRepository: PlaceRecommendationsRepository
) : ViewModel() {
    private val _placeRecommendationsUiState = MutableStateFlow(PlaceRecommendationsUiState())
    val placeRecommendationsUiState= _placeRecommendationsUiState.asStateFlow()

    fun getPlaceRecommendations() {
        _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            runCatching {
                placeRecommendationsRepository.fetchPlaceRecommendations()
            }.onSuccess { data ->
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isLoading = false,
                    recommendations = data.recommendations.map {
                        PlaceRecommendationsUiState.RecommendationsUiState(
                            id = it.id,
                            recommendationType = it.recommendationType,
                            name = it.name,
                            address = it.address,
                            shortIntroduction = it.shortIntroduction,
                            placeCategory = it.placeCategory,
                            placeArea = it.placeArea,
                            latitude = it.latitude,
                            longitude = it.longitude,
                            categoryImageUrl = it.categoryImageUrl
                        )
                    }
                )
            }.onFailure { t ->
                _placeRecommendationsUiState.value = _placeRecommendationsUiState.value.copy(
                    isLoading = false,
                    errorMessage = t.message.toString(),
                    recommendations = emptyList()
                )
            }
        }
    }
}