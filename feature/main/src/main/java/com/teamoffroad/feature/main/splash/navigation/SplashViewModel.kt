package com.teamoffroad.feature.main.splash.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.core.common.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.feature.main.splash.SplashUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
) : ViewModel() {
    private val _splashUiState = MutableSharedFlow<SplashUiState>()
    val splashUiState: SharedFlow<SplashUiState> get() = _splashUiState.asSharedFlow()

    init {
        checkAutoSignIn()
    }

    private fun checkAutoSignIn() {
        viewModelScope.launch {
            val isAuthSignIn = getAutoSignInUseCase()
            if (isAuthSignIn.first()) {
                _splashUiState.emit(SplashUiState.NavigateHome)
            } else {
                _splashUiState.emit(SplashUiState.NavigateLogin)
            }
        }
    }
}