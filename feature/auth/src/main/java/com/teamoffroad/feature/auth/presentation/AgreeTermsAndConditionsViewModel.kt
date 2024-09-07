package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.usecase.UserMarketingAgreeUseCase
import com.teamoffroad.feature.auth.presentation.model.AgreeTermsAndConditionsUiState
import com.teamoffroad.feature.auth.presentation.model.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgreeTermsAndConditionsViewModel @Inject constructor(
    private val marketingInfoUseCase: UserMarketingAgreeUseCase,
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
        if (allChecked.value) {
            _allChecked.value = false
            _serviceUtil.value = false
            _personalInfo.value = false
            _location.value = false
            _marketing.value = false
        } else if (!allChecked.value) {
            _allChecked.value = true
            _serviceUtil.value = true
            _personalInfo.value = true
            _location.value = true
            _marketing.value = true
        }
    }

    fun serviceCheckedChangedListener() {
        _serviceUtil.value = !serviceUtil.value
    }

    fun personalCheckedChangedListener() {
        _personalInfo.value = !personalInfo.value
    }

    fun locationCheckedChangedListener() {
        _location.value = !location.value
    }

    fun marketingCheckedChangedListener() {
        _marketing.value = !marketing.value
    }

    fun serviceDialogCheckedChangedListener() {
        if (!serviceUtil.value) {
            _serviceUtil.value = true
        }
    }

    fun personalDialogCheckedChangedListener() {
        if (!personalInfo.value) {
            _personalInfo.value = true
        }
    }

    fun locationDialogCheckedChangedListener() {
        if (!location.value) {
            _location.value = true
        }
    }

    fun marketingDialogCheckedChangedListener() {
        if (!marketing.value) {
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

    fun changedMarketingAgree(marketingAgree: Boolean) {
        viewModelScope.launch {
            runCatching {
                marketingInfoUseCase.invoke(marketingAgree)
            }.onSuccess {
                _dialogState.value =
                    DialogState.EMPTY
            }.onFailure {
                //TODO. 마케팅 동의 api 전송이 실패했을때
            }
        }
    }
}