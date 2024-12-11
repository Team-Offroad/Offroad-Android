package com.teamoffroad.feature.auth.presentation.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class SignUpViewModel @Inject constructor(

) : ViewModel() {
    private val _signUpUiState: MutableStateFlow<SignUpState> = MutableStateFlow(SignUpState())
    val signUpUiState: StateFlow<SignUpState> = _signUpUiState.asStateFlow()

    private val _signUpSideEffect: Channel<SignUpSideEffect> = Channel()
    val signUpSideEffect = _signUpSideEffect.receiveAsFlow()
}