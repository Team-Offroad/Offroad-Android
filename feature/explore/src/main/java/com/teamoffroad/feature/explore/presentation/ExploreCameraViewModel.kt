package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.explore.domain.usecase.PostExploreAuthUseCase
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreCameraViewModel @Inject constructor(
    private val postExploreAuthUseCase: PostExploreAuthUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExploreCameraUiState> = MutableStateFlow(ExploreCameraUiState.None)
    val uiState: StateFlow<ExploreCameraUiState> = _uiState.asStateFlow()

    private val _successImageUrl: MutableStateFlow<String> = MutableStateFlow("None")
    val successImageUrl: StateFlow<String> = _successImageUrl.asStateFlow()

    fun postExploreResult(placeId: Long, latitude: Double, longitude: Double, qr: String) {
        if (uiState.value is ExploreCameraUiState.Loading) return
        _uiState.value = ExploreCameraUiState.Loading

        viewModelScope.launch {
            runCatching {
                postExploreAuthUseCase.invoke(placeId, qr, latitude, longitude)
            }.onSuccess { exploreResult ->
                when (exploreResult.isQRMatched) {
                    true -> {
                        _uiState.value = ExploreCameraUiState.Success
                        _successImageUrl.value = exploreResult.characterImageUrl
                    }

                    false -> _uiState.value = ExploreCameraUiState.CodeError
                }
            }.onFailure {
                when (it.message) {
                    "HTTP 400" -> _uiState.value = ExploreCameraUiState.LocationError
                    else -> _uiState.value = ExploreCameraUiState.EtcError
                }
            }
        }
    }
}
