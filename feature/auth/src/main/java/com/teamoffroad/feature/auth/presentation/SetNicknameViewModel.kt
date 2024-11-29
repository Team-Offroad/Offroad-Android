package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.domain.usecase.GetNicknameValidateUseCase
import com.teamoffroad.feature.auth.presentation.component.NicknameValidateResult
import com.teamoffroad.feature.auth.presentation.model.SetNicknameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetNicknameViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val getNicknameValidateUseCase: GetNicknameValidateUseCase,
) : ViewModel() {
    private val _nicknameUiState: MutableStateFlow<SetNicknameUiState> = MutableStateFlow(
        SetNicknameUiState()
    )
    val nicknameUiState: StateFlow<SetNicknameUiState> = _nicknameUiState.asStateFlow()

    fun updateNicknamesValid(nickname: String) {
            _nicknameUiState.value =
                SetNicknameUiState(nickname, getNicknameValidateUseCase.invoke(nickname))

    }

    fun getDuplicateNickname() {
        viewModelScope.launch {
            runCatching { authRepository.fetchDuplicateNickname(_nicknameUiState.value.nickname) }
                .onSuccess {
                    when (it) {
                        true -> {
                            _nicknameUiState.value =
                                SetNicknameUiState(
                                    _nicknameUiState.value.nickname,
                                    NicknameValidateResult.Duplicate
                                )
                        }

                        false -> {
                            _nicknameUiState.value =
                                SetNicknameUiState(
                                    _nicknameUiState.value.nickname,
                                    NicknameValidateResult.Success
                                )
                        }
                    }
                }
                .onFailure {
                    _nicknameUiState.value =
                        _nicknameUiState.value.copy(nicknameValidateResult = NicknameValidateResult.Error)
                }
        }
    }
}