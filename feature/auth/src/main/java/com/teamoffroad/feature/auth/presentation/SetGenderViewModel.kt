package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetGenderViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _selectedGender: MutableStateFlow<String?> = MutableStateFlow(null)
    val selectedGender: StateFlow<String?> = _selectedGender.asStateFlow()

    fun updateCheckedGender(gender: String) {
        _selectedGender.value = gender
    }

    private val _isSuccess: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSuccess: StateFlow<Boolean> = _isSuccess.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    fun fetchUserProfile(nickname: String, birthDate: String?) {
        viewModelScope.launch {
            runCatching {
                authRepository.patchUserProfile(
                    userProfile = UserProfile(
                        nickname = nickname,
                        year = birthDate?.split("-")?.get(0)?.toInt(),
                        month = birthDate?.split("-")?.get(1)?.toInt(),
                        day = birthDate?.split("-")?.get(2)?.toInt(),
                        gender = selectedGender.value,
                    )
                )
            }.onSuccess {
                _isSuccess.value = true
                _isLoading.value = false
            }.onFailure {
                _isError.value = true
                _isLoading.value = false
            }
        }
    }
}