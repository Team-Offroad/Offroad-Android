package com.teamoffroad.feature.mypage.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.teamoffroad.feature.auth.domain.usecase.ClearDataStoreUseCase
import com.teamoffroad.feature.auth.domain.usecase.UserMarketingAgreeUseCase
import com.teamoffroad.feature.mypage.domain.usecase.DeleteUserInfoUseCase
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
    private val marketingInfoUseCase: UserMarketingAgreeUseCase,
    private val deleteUserInfoUseCase: DeleteUserInfoUseCase,
    private val googleSignInClient: GoogleSignInClient,
    private val clearDataStoreUseCase: ClearDataStoreUseCase
) : ViewModel() {
    private val _settingUiState: MutableStateFlow<SettingUiState> =
        MutableStateFlow(SettingUiState())
    val settingUiState: StateFlow<SettingUiState> = _settingUiState.asStateFlow()

    fun changeDialogState(settingDialogState: SettingDialogState) {
        when (settingDialogState) {
            SettingDialogState.InVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.InVisible, "", false)

            SettingDialogState.LogoutVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.LogoutVisible)

            SettingDialogState.MarketingVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.MarketingVisible)

            SettingDialogState.WithDrawVisible -> _settingUiState.value =
                _settingUiState.value.copy(SettingDialogState.WithDrawVisible)
        }
    }

    fun changeWithDrawInputText(text: String) {
        _settingUiState.value = settingUiState.value.copy(withDrawInputState = text)
    }

    fun changeWithDrawInputTextResult() {
        _settingUiState.value = settingUiState.value.copy(withDrawResult = true)
    }

    fun deleteUserInfo(deleteCode: String) {
        viewModelScope.launch {
            deleteUserInfoUseCase.invoke(deleteCode)
        }
    }

    fun changedMarketingAgree(marketingAgree: Boolean) {
        viewModelScope.launch {
            marketingInfoUseCase.invoke(marketingAgree)
        }
    }

    fun performSignOut() {
        viewModelScope.launch {
            runCatching { googleSignInClient.signOut() }
                .onSuccess { clearDataStoreUseCase.invoke() }
                .onFailure { it.message.toString() }
        }
    }
}