package com.teamoffroad.feature.recommendplace.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import com.teamoffroad.feature.recommendplace.presentation.model.PlaceOrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecommendPlaceOrderViewModel @Inject constructor(
    private val placeRecommendationsRepository: PlaceRecommendationsRepository,
) : ViewModel() {
    private val _placeOrderUiState = MutableStateFlow(PlaceOrderUiState())
    val placeOrderUiState = _placeOrderUiState.asStateFlow()

    fun savePlaceRecommendationsOrder(order: PlaceRecommendationsOrder) {
        viewModelScope.launch {
            runCatching {
                _placeOrderUiState.value = _placeOrderUiState.value.copy(
                    isLoading = true
                )
                placeRecommendationsRepository.postPlaceRecommendationsOrder(order)
            }.onSuccess { state ->
                _placeOrderUiState.value = _placeOrderUiState.value.copy(
                    isLoading = false,
                    content = state
                )
            }.onFailure { t ->
                _placeOrderUiState.value = _placeOrderUiState.value.copy(
                    isLoading = false,
                    errorMessage = t.message.toString()
                )
            }
        }
    }
}