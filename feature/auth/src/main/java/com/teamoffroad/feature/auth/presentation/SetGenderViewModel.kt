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
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _isCheckedGender: MutableStateFlow<String> = MutableStateFlow("")
    val isCheckedGender: StateFlow<String> = _isCheckedGender.asStateFlow()

    fun updateCheckedGender(gender: String) {
        _isCheckedGender.value = gender
    }

    fun patchUserProfile() {
        val userProfile = UserProfile(
            "asdasd",
            2000,
            2,
            22,
            "MALE"
        )

        viewModelScope.launch {
            authRepository.patchUserProfile(userProfile)
        }
    }
}