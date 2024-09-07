package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import com.teamoffroad.feature.auth.presentation.model.AgreeTermsAndConditionsUiState
import com.teamoffroad.feature.auth.presentation.model.DialogState
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
    private val _dialogState: MutableStateFlow<DialogState> = MutableStateFlow(DialogState.EMPTY)
    val dialogState: StateFlow<DialogState> = _dialogState.asStateFlow()
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

    fun serviceDialogCheckedChangedListener() {
        if (!_serviceUtil.value) {
            _serviceUtil.value = true
        }
    }

    fun personalDialogCheckedChangedListener() {
        if (!_personalInfo.value) {
            _personalInfo.value = true
        }
    }

    fun locationDialogCheckedChangedListener() {
        if (!_location.value) {
            _location.value = true
        }
    }

    fun marketingDialogCheckedChangedListener() {
        if (!_marketing.value) {
            _marketing.value = true
        }
    }

    fun updateAgreeTermsAndConditionsUiState(required: Boolean) {
        if (required)
            _agreeTermsAndConditionsUiState.value = AgreeTermsAndConditionsUiState.REQUIRED
        else
            _agreeTermsAndConditionsUiState.value = AgreeTermsAndConditionsUiState.EMPTY

    }

    fun changeDialogState(dialogState: DialogState) {
        when (dialogState) {
            DialogState.SERVICE_DIALOG -> _dialogState.value =
                DialogState.SERVICE_DIALOG

            DialogState.PERSONAL_DIALOG -> _dialogState.value =
                DialogState.PERSONAL_DIALOG

            DialogState.LOCATION_DIALOG -> _dialogState.value =
                DialogState.LOCATION_DIALOG

            DialogState.MARKETING_DIALOG -> _dialogState.value =
                DialogState.MARKETING_DIALOG

            DialogState.EMPTY -> _dialogState.value =
                DialogState.EMPTY
        }

    }
}