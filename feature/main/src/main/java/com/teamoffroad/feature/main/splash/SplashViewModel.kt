package com.teamoffroad.feature.main.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.core.common.domain.usecase.GetAutoSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
) : ViewModel() {
    private val _sideEffects = MutableSharedFlow<SplashSideEffect>()
    val sideEffects: SharedFlow<SplashSideEffect> get() = _sideEffects.asSharedFlow()

    fun checkAutoSignIn() {
        viewModelScope.launch {
            delay(1600L)
            getAutoSignInUseCase().collect { isAutoSignIn ->
                when (isAutoSignIn) {
                    true -> _sideEffects.emit(SplashSideEffect.NavigateToHome)
                    false -> _sideEffects.emit(SplashSideEffect.NavigateLogin)
                }
            }
        }
    }
}