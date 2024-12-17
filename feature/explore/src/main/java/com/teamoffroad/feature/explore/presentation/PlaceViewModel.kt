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
                    isAdditionalLoading = true,
                    isError = false,
                )
                val places = uiState.value.visitedPlaces + uiState.value.unvisitedPlaces
                val cursorDistance = if (places.isEmpty()) null else places.maxOf { it.distanceFromUser }
                val count = if (places.isEmpty()) INITIAL_LOAD_LIMIT else ADDITIONAL_LOAD_LIMIT
                getPlaceListUseCase(0.0, 0.0, count, cursorDistance)
            }.onSuccess { places ->
                _uiState.value = uiState.value.copy(
                    visitedPlaces = uiState.value.visitedPlaces + places.map { it.toUi() }.filter { it.isVisited },
                    unvisitedPlaces = uiState.value.unvisitedPlaces + places.map { it.toUi() }.filter { it.isVisited.not() },
                    isLoading = false,
                    isLoadable = places.isEmpty().not(),
                    isAdditionalLoading = false,
                    isError = false,
                )
            }.onFailure {
                _uiState.value = uiState.value.copy(
                    isLoading = false,
                    isAdditionalLoading = false,
                    isError = true,
                )
            }
        }
    }
}

private const val INITIAL_LOAD_LIMIT = 30
private const val ADDITIONAL_LOAD_LIMIT = 15
