package com.teamoffroad.feature.auth.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamoffroad.core.common.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.core.common.domain.usecase.SaveAccessTokenUseCase
import com.teamoffroad.core.common.domain.usecase.SaveRefreshTokenUseCase
import com.teamoffroad.feature.auth.domain.model.SocialSignInPlatform
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
) : ViewModel() {
    private val _signInUiState: MutableStateFlow<SignInUiState> = MutableStateFlow(SignInUiState())
    val signInUiState: StateFlow<SignInUiState> = _signInUiState.asStateFlow()

    private val _signInSideEffect: Channel<Boolean> = Channel()
    val sideEffect = _signInSideEffect.receiveAsFlow()

    fun startKakaoSignIn() {
        _signInUiState.value = signInUiState.value.copy(
            startKakaoSignIn = true
        )
    }

    fun startGoogleSignIn() {
        _signInUiState.value = signInUiState.value.copy(
            startGoogleSignIn = true
        )
    }

    fun performGoogleSignIn(googleIdToken: String) {
        viewModelScope.launch {
            signIn(
                SocialSignInPlatform.GOOGLE.name,
                null,
                googleIdToken
            )
        }
    }

    fun performKakaoSignIn(kakaoAuthCode: String) {
        viewModelScope.launch {
            signIn(
                SocialSignInPlatform.KAKAO.name,
                null,
                kakaoAuthCode,
            )
        }
    }

    private suspend fun signIn(socialPlatform: String, name: String?, code: String) {
        viewModelScope.launch {
            runCatching {
                authUseCase.invoke(socialPlatform, name, code)
            }.onSuccess { signInInfo ->
                saveAccessTokenUseCase.invoke(signInInfo.tokens.accessToken)
                saveRefreshTokenUseCase.invoke(signInInfo.tokens.refreshToken)
                _signInUiState.value = _signInUiState.value.copy(
                    signInSuccess = true,
                    alreadyExist = signInInfo.isAlreadyExist
                )
            }.onFailure {
                initState()
            }
        }
    }

    fun checkAutoSignIn() {
        viewModelScope.launch {
            getAutoSignInUseCase().collect { isAutoSignIn ->
                _signInUiState.value = _signInUiState.value.copy(
                    isAutoSignIn = isAutoSignIn
                )
            }
        }
    }

    fun updateSignInResult() {
        viewModelScope.launch {
            _signInSideEffect.send(true)
        }
    }

    fun initState() {
        _signInUiState.value = SignInUiState()
    }
}
