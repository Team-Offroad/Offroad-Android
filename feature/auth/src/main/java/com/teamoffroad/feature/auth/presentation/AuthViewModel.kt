package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AuthViewModel @Inject constructor(
) : ViewModel() {
    private val _isNicknameValid: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNicknameValid: StateFlow<Boolean> = _isNicknameValid.asStateFlow()

    fun updateNicknameValid(nickname: String) {
        _isNicknameValid.value = true
    }
}