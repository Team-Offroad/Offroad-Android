package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.feature.auth.presentation.model.AgreeTermsAndConditionsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AgreeTermsAndConditionsViewModel @Inject constructor(

) : ViewModel() {
    private val _allChecked: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val allChecked: StateFlow<Boolean> = _allChecked.asStateFlow()
    private val _serviceUtil: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val serviceUtil: StateFlow<Boolean> = _serviceUtil.asStateFlow()
    private val _personalInfo: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val personalInfo: StateFlow<Boolean> = _personalInfo.asStateFlow()
    private val _location: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val location: StateFlow<Boolean> = _location.asStateFlow()
    private val _marketing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val marketing: StateFlow<Boolean> = _marketing.asStateFlow()

    private val _agreeTermsAndConditionsUiState: MutableStateFlow<AgreeTermsAndConditionsUiState> =
        MutableStateFlow(
            AgreeTermsAndConditionsUiState.EMPTY
        )
    val agreeTermsAndConditionsUiState: StateFlow<AgreeTermsAndConditionsUiState> =
        _agreeTermsAndConditionsUiState.asStateFlow()

    fun allCheckedChangedListener() {
        if (_allChecked.value) {
            _allChecked.value = false
            _serviceUtil.value = false
            _personalInfo.value = false
            _location.value = false
            _marketing.value = false
        } else if (!_allChecked.value) {
            _allChecked.value = true
            _serviceUtil.value = true
            _personalInfo.value = true
            _location.value = true
            _marketing.value = true
        }
    }

    fun serviceCheckedChangedListener() {
        _serviceUtil.value = !_serviceUtil.value
    }

    fun personalCheckedChangedListener() {
        _personalInfo.value = !_personalInfo.value
    }

    fun locationCheckedChangedListener() {
        _location.value = !_location.value
    }

    fun marketingCheckedChangedListener() {
        _marketing.value = !_marketing.value
    }

    fun updateAgreeTermsAndConditionsUiState(required: Boolean) {
        if (required)
            _agreeTermsAndConditionsUiState.value = AgreeTermsAndConditionsUiState.REQUIRED
        else
            _agreeTermsAndConditionsUiState.value = AgreeTermsAndConditionsUiState.EMPTY

    }
}