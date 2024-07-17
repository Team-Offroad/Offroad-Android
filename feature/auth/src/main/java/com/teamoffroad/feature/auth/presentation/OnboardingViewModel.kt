package com.teamoffroad.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isCheckedNickname: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedNickname: StateFlow<String> = _isCheckedNickname.asStateFlow()

    private val _isCheckedNicknameState: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isCheckedNicknameState: StateFlow<Boolean> = _isCheckedNicknameState.asStateFlow()

    private val _isNicknameState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNicknameState: StateFlow<Boolean> = _isNicknameState.asStateFlow()

    private val _inputNickname: MutableStateFlow<String> = MutableStateFlow("")
    val inputNickname: StateFlow<String> = _inputNickname.asStateFlow()


    //검사 후 업데이트 닉네임
    fun updateNicknameValid(nickname: String) {
        _isCheckedNickname.value = nickname
        Log.d(
            "asdasd", _isCheckedNickname.value
        )
    }

    fun updateInputNickname(nickname: String) {
        _inputNickname.value = nickname
    }

    fun updateDuplicateState(state: Boolean) {
        _isNicknameState.value = state
    }

    fun updateCheckedNickname(nickname: String) {
        _isCheckedNickname.value = nickname
    }

    fun getDuplicateNickname(nickname: String) {
        viewModelScope.launch {
            runCatching { authRepository.getDuplicateNickname(nickname) }
                .onSuccess {
                    updateDuplicateState(!it)
                }
                .onFailure {}
        }
    }
}