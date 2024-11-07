package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.core.common.domain.usecase.UpdateAutoSignInUseCase
import com.teamoffroad.feature.auth.presentation.model.SetGenderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetGenderViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val updateAutoSignInUseCase: UpdateAutoSignInUseCase,
) : ViewModel() {

    private val _genderUiState = MutableStateFlow<SetGenderUiState>(SetGenderUiState.Loading)
    val genderUiState: StateFlow<SetGenderUiState> = _genderUiState.asStateFlow()

    fun updateGenderEmpty() {
        _genderUiState.value = SetGenderUiState.Loading
    }

    fun updateCheckedGender(gender: String) {
        _genderUiState.value = SetGenderUiState.Select(gender)
    }

    fun fetchUserProfile(
        nickname: String,
        birthDate: String?,
        gender: String? = when (val state = genderUiState.value) {
            is SetGenderUiState.Select -> state.selectedGender
            else -> null
        },
    ) {
        viewModelScope.launch {
            runCatching {
                authRepository.saveUserProfile(
                    userProfile = UserProfile(
                        nickname = nickname,
                        year = birthDate?.split("-")?.get(0)?.toInt(),
                        month = birthDate?.split("-")?.get(1)?.toInt(),
                        day = birthDate?.split("-")?.get(2)?.toInt(),
                        gender = gender,
                    )
                )
            }.onSuccess {
                _genderUiState.value = SetGenderUiState.Success
                updateAutoSignInUseCase.invoke(true)
            }.onFailure {
                _genderUiState.value = SetGenderUiState.Error
            }
        }
    }
}