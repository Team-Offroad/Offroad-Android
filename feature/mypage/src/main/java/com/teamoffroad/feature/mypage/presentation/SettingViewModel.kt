package com.teamoffroad.feature.mypage.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.teamoffroad.core.common.domain.usecase.ClearTokensUseCase
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.feature.auth.domain.usecase.UserMarketingAgreeUseCase
import com.teamoffroad.feature.mypage.domain.usecase.DeleteUserInfoUseCase
import com.teamoffroad.feature.mypage.presentation.component.SettingDialogState
import com.teamoffroad.feature.mypage.presentation.model.SettingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val marketingInfoUseCase: UserMarketingAgreeUseCase,
    private val deleteUserInfoUseCase: DeleteUserInfoUseCase,
    private val clearTokensUseCase: ClearTokensUseCase,
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
    @ApplicationContext private val context: Context,
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
        _settingUiState.value = when (text) {
            "오프로드 회원을 탈퇴하겠습니다." -> settingUiState.value.copy(withDrawResult = true)
            else -> settingUiState.value.copy(withDrawResult = false)
        }
    }

    fun deleteUserInfo(deleteCode: String) {
        viewModelScope.launch {
            deleteUserInfoUseCase.invoke(deleteCode)
            performSignOut()
        }
    }

    fun changedMarketingAgree(marketingAgree: Boolean) {
        viewModelScope.launch {
            _settingUiState.value = settingUiState.value.copy(marketingAgree = marketingAgree)
            marketingInfoUseCase.invoke(marketingAgree)
        }
    }

    fun performSignOut() {
        viewModelScope.launch {
            runCatching {
                clearTokensUseCase()
                setAutoSignInUseCase.invoke(false)
            }
                .onSuccess {
                    _settingUiState.value = _settingUiState.value.copy(reset = true)
                }
                .onFailure {
                    it.message.toString()
                }
        }
    }
}
