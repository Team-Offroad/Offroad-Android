package com.teamoffroad.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
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
        Log.e("asdasd", _inputNickname.value.toString())
    }

    fun updateCheckedNickname(nickname: String) {
        _isCheckedNickname.value = nickname
    }

    fun updateCheckedYear(year: String) {
        _isCheckedYear.value = year
        Log.e("asdsad", _isCheckedYear.value)
    }

    fun updateCheckedMonth(month: String) {
        _isCheckedMonth.value = month
        Log.e("asdsad", _isCheckedMonth.value)

    }

    fun updateCheckedDate(day: String) {
        _isCheckedDay.value = day
        Log.e("asdsad", _isCheckedDay.value)

    }

    fun updateCheckedGender(gender: String) {
        _isCheckedGender.value = gender
    }
}