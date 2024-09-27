package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.usecase.GetPlaceListUseCase
import com.teamoffroad.feature.explore.presentation.mapper.toUi
import com.teamoffroad.feature.explore.presentation.model.PlaceUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceViewModel @Inject constructor(
    private val getPlaceListUseCase: GetPlaceListUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<PlaceUiState> = MutableStateFlow(PlaceUiState())
    val uiState: StateFlow<PlaceUiState> = _uiState.asStateFlow()

    fun updatePlaces() {
        viewModelScope.launch {
            runCatching {
                _uiState.value = uiState.value.copy(
                    loading = true,
                    error = false,
                )
                getPlaceListUseCase(0.0, 0.0, 10000000, false)
            }.onSuccess { places ->
                _uiState.value = uiState.value.copy(
                    visitedPlaces = places.map { it.toUi() }.filter { it.isVisited },
                    unvisitedPlaces = places.map { it.toUi() }.filter { it.isVisited.not() },
                    loading = false,
                    error = false,
                )
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    loading = false,
                    error = true,
                )
            }
        }
    }
}
