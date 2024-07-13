package com.teamoffroad.feature.explore.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.feature.explore.presentation.model.ExploreUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(

) : ViewModel() {

    private val _uiState: MutableStateFlow<ExploreUiState> = MutableStateFlow(ExploreUiState())
    val uiState: StateFlow<ExploreUiState> get() = _uiState

    fun updatePermission(isAlreadyHavePermission: Boolean, isPermissionRejected: Boolean) {
        _uiState.value = uiState.value.copy(
            isAlreadyHavePermission = isAlreadyHavePermission,
            isPermissionRejected = isPermissionRejected
        )
    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _uiState.value = uiState.value.copy(
            locationModel = uiState.value.locationModel.updateLocation(latitude, longitude)
        )
    }

    fun updateTrackingToggle(isUserTrackingEnabled: Boolean) {
        _uiState.value = uiState.value.copy(
            locationModel = uiState.value.locationModel.updateTrackingToggle(isUserTrackingEnabled)
        )
    }
}
