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

    private val _isCheckedYear: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedYear: StateFlow<String> = _isCheckedYear.asStateFlow()

    private val _isCheckedMonth: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedMonth: StateFlow<String> = _isCheckedMonth.asStateFlow()

    private val _isCheckedDay: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedDay: StateFlow<String> = _isCheckedDay.asStateFlow()

    private val _isCheckedGender: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedGender: StateFlow<String> = _isCheckedGender.asStateFlow()

    private val _isNicknameValid: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNicknameValid: StateFlow<Boolean> = _isNicknameValid.asStateFlow()

    private val _inputNickname: MutableStateFlow<String> = MutableStateFlow("")
    val inputNickname: StateFlow<String> = _inputNickname.asStateFlow()


    fun updateNicknameValid(nickname: String) {
        _isNicknameValid.value = true
    }

    fun updateInputNickname(nickname: String) {
        _inputNickname.value = nickname
    }

    fun updateCheckedNickname(nickname: String) {
        _isCheckedNickname.value = nickname
    }

    fun updateCheckedYear(year: String) {
        _isCheckedYear.value = year
    }

    fun updateCheckedMonth(month: String) {
        _isCheckedMonth.value = month
    }

    fun updateCheckedDate(day: String) {
        _isCheckedDay.value = day
    }

    fun updateCheckedGender(gender: String) {
        _isCheckedGender.value = gender
    }

    fun getDuplicateNickname(nickname: String) {
        viewModelScope.launch {
            runCatching { authRepository.getDuplicateNickname(nickname) }
                .onSuccess {
                    Log.e("asdasd", it.toString())
                }
                .onFailure { Log.e("asdasd", "fail") }
        }


    }
}