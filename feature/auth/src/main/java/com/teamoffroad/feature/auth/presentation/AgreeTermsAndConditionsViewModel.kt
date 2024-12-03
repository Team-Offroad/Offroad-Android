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
    private val _dialogState: MutableStateFlow<DialogState> = MutableStateFlow(DialogState.EMPTY)
    val dialogState: StateFlow<DialogState> = _dialogState.asStateFlow()

    private val _uiState: MutableStateFlow<AgreeTermsAndConditionsUiState> =
        MutableStateFlow(AgreeTermsAndConditionsUiState())
    val uiState: StateFlow<AgreeTermsAndConditionsUiState> = _uiState.asStateFlow()


    fun allCheckedChangedListener() {
        if (!uiState.value.isServiceUtil || !uiState.value.isPersonalInfo || !uiState.value.isLocation || !uiState.value.isMarketing)
            _uiState.value = uiState.value.copy(
                isServiceUtil = true,
                isPersonalInfo = true,
                isLocation = true,
                isMarketing = true,
            )
        else _uiState.value = uiState.value.copy(
            isServiceUtil = false,
            isPersonalInfo = false,
            isLocation = false,
            isMarketing = false,
        )
    }

    fun serviceCheckedChangedListener() {
        if (!uiState.value.isServiceUtil)
            _uiState.value = uiState.value.copy(
                isServiceUtil = true
            )
        else _uiState.value = uiState.value.copy(
            isServiceUtil = false
        )
    }

    fun personalCheckedChangedListener() {
        if (!uiState.value.isPersonalInfo)
            _uiState.value = uiState.value.copy(
                isPersonalInfo = true
            )
        else _uiState.value = uiState.value.copy(
            isPersonalInfo = false
        )
    }

    fun locationCheckedChangedListener() {
        if (!uiState.value.isLocation)
            _uiState.value = uiState.value.copy(
                isLocation = true
            )
        else _uiState.value = uiState.value.copy(
            isLocation = false
        )
    }

    fun marketingCheckedChangedListener() {
        if (!uiState.value.isMarketing)
            _uiState.value = uiState.value.copy(
                isMarketing = true
            )
        else _uiState.value = uiState.value.copy(
            isMarketing = false
        )
    }

    fun serviceDialogCheckedChangedListener(dialogClickState: Boolean) {
        when (dialogClickState) {
            true -> _uiState.value = uiState.value.copy(
                isServiceUtil = true
            )

            false -> _uiState.value = uiState.value.copy(
                isServiceUtil = false
            )
        }
    }

    fun personalDialogCheckedChangedListener(dialogClickState: Boolean) {
        when (dialogClickState) {
            true -> _uiState.value = uiState.value.copy(
                isPersonalInfo = true
            )

            false -> _uiState.value = uiState.value.copy(
                isPersonalInfo = false
            )
        }
    }

    fun locationDialogCheckedChangedListener(dialogClickState: Boolean) {
        when (dialogClickState) {
            true -> _uiState.value = uiState.value.copy(
                isLocation = true
            )

            false -> _uiState.value = uiState.value.copy(
                isLocation = false
            )
        }
    }

    fun marketingDialogCheckedChangedListener(dialogClickState: Boolean) {
        when (dialogClickState) {
            true -> _uiState.value = uiState.value.copy(
                isMarketing = true
            )

            false -> _uiState.value = uiState.value.copy(
                isMarketing = false
            )
        }
    }

    fun updateAgreeTermsAndConditionsUiState() {
        if (uiState.value.isServiceUtil && uiState.value.isPersonalInfo && uiState.value.isLocation)
            _uiState.value = uiState.value.copy(
                success = true
            )
        else
            _uiState.value = uiState.value.copy(
                success = false
            )
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
            }.onFailure { exception ->
                exception.printStackTrace()
            }
        }
    }
}