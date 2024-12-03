package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import com.teamoffroad.feature.auth.presentation.model.SetGenderStateResult
import com.teamoffroad.feature.auth.presentation.model.SetGenderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetGenderViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _genderUiState: MutableStateFlow<SetGenderUiState> = MutableStateFlow(
        SetGenderUiState()
    )
    val genderUiState: StateFlow<SetGenderUiState> = _genderUiState.asStateFlow()

    private val _sideEffect: Channel<Boolean> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun updateGenderEmpty() {
        _genderUiState.value = genderUiState.value.copy(
            genderResult = SetGenderStateResult.Empty,
        )
    }

    fun updateCheckedGender(gender: String) {
        if (gender == genderUiState.value.selectedGender) {
            _genderUiState.value = genderUiState.value.copy(
                selectedGender = "",
                genderResult = SetGenderStateResult.Empty

            )
        } else
            _genderUiState.value = genderUiState.value.copy(
                selectedGender = gender,
                genderResult = SetGenderStateResult.Select
            )
    }

    fun fetchUserProfile(
        nickname: String,
        birthDate: String?,
        gender: String? = when (genderUiState.value.genderResult) {
            is SetGenderStateResult.Select -> genderUiState.value.selectedGender
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
                _sideEffect.send(true)
            }.onFailure {
                _sideEffect.send(false)

            }
        }
    }

    fun initGenderState() {
        _genderUiState.value = SetGenderUiState()
    }
}