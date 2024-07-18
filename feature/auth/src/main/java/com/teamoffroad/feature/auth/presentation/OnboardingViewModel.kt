package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.presentation.model.NicknameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _isCheckedNickname: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedNickname: StateFlow<String> = _isCheckedNickname.asStateFlow()

    private val _isCheckedNicknameState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isCheckedNicknameState: StateFlow<Boolean> = _isCheckedNicknameState.asStateFlow()

    private val _isNicknameState: MutableStateFlow<NicknameUiState> = MutableStateFlow(NicknameUiState.Empty)
    val isNicknameState: StateFlow<NicknameUiState> = _isNicknameState.asStateFlow()

    private val _inputNickname: MutableStateFlow<String> = MutableStateFlow("")
    val inputNickname: StateFlow<String> = _inputNickname.asStateFlow()

    fun updateNicknameValid(nickname: String) {
        _isCheckedNickname.value = nickname
    }

    fun updateInputNickname(nickname: String) {
        _inputNickname.value = nickname
    }

    fun updateDuplicateState(state: Boolean) {
        when(state){
            true -> _isNicknameState.value = NicknameUiState.Duplicated
            false -> _isNicknameState.value = NicknameUiState.UnDuplicated
        }
    }

    fun updateCheckedNickname(nickname: String) {
        _isCheckedNickname.value = nickname
    }

    fun getDuplicateNickname(nickname: String) {
        viewModelScope.launch {
            runCatching { authRepository.getDuplicateNickname(nickname) }
                .onSuccess {
                    updateDuplicateState(it)
                }
                .onFailure {}
        }
    }
}