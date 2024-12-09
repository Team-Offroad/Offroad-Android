package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
) : ViewModel() {

    private val _genderUiState: MutableStateFlow<SetGenderUiState> = MutableStateFlow(
        SetGenderUiState()
    )
    val genderUiState: StateFlow<SetGenderUiState> = _genderUiState.asStateFlow()

    private val _sideEffect: Channel<Boolean> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun updateCheckedGender(gender: String) {
        if (gender == genderUiState.value.selectedGender) {
            _genderUiState.value = genderUiState.value.copy(
                selectedGender = null,
                genderResult = SetGenderStateResult.Empty

            )
        } else
            _genderUiState.value = genderUiState.value.copy(
                selectedGender = gender,
                genderResult = SetGenderStateResult.Select
            )
    }

    fun saveGenderState() {
        viewModelScope.launch {
            _sideEffect.send(true)
        }
    }

    fun initGenderState() {
        _genderUiState.value = SetGenderUiState()
    }
}