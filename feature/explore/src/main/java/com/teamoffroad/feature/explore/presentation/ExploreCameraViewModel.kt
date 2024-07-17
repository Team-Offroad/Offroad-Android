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

    private val _uiState: MutableStateFlow<ExploreCameraUiState> = MutableStateFlow(ExploreCameraUiState.Loading)
    val uiState: StateFlow<ExploreCameraUiState> = _uiState.asStateFlow()

    private val _successImageUrl: MutableStateFlow<String> = MutableStateFlow("None")
    val successImageUrl: StateFlow<String> = _successImageUrl.asStateFlow()

    fun postExploreResult(placeId: Long, latitude: Double, longitude: Double, qr: String) {
        viewModelScope.launch {
            runCatching {
                postExploreAuthUseCase.invoke(placeId, qr, latitude, longitude)
            }.onSuccess {
                when (it) {
                    true -> {
                        _uiState.value = ExploreCameraUiState.Success
                        _successImageUrl.value = "https://github.com/user-attachments/assets/a5895ae1-f76d-4903-8c6d-b7fac8ccabc5"
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
