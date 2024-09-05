package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.teamoffroad.feature.mypage.domain.usecase.UserMarketingInfoUseCase
import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState
import com.teamoffroad.feature.mypage.presentation.model.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val marketingInfoUseCase: UserMarketingInfoUseCase,
    private val googleSignInClient: GoogleSignInClient,
) : ViewModel() {
    private val _settingUiState: MutableStateFlow<SettingUiState> =
        MutableStateFlow(SettingUiState())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState.asStateFlow()

    fun changeDialogState(settingDialogState: SettingDialogState) {
        when (settingDialogState) {
            SettingDialogState.inVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.inVisible, "", false)

            SettingDialogState.logoutVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.logoutVisible)

            SettingDialogState.marketingVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.marketingVisible)

            SettingDialogState.withDrawVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.withDrawVisible)
        }
    }

    fun changeWithDrawInputText(text: String) {
        _settingUiState.value = _settingUiState.value.copy(withDrawInputState = text)
    }

    fun changeWithDrawInputTextResult() {
        _settingUiState.value = _settingUiState.value.copy(withDrawResult = true)
    }

    fun patchMarketingInfo() {
        viewModelScope.launch {
            kotlin.runCatching {
                marketingInfoUseCase.invoke()
            }.onSuccess { it.getOrNull() }
                .onFailure { it.message }
        }
    }

    fun performSignOut() {
        googleSignInClient.signOut()
    }
}