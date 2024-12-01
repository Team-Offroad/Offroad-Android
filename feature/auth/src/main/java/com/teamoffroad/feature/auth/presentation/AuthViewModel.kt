package com.teamoffroad.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.teamoffroad.core.common.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.core.common.domain.usecase.SaveAccessTokenUseCase
import com.teamoffroad.core.common.domain.usecase.SaveRefreshTokenUseCase
import com.teamoffroad.feature.auth.domain.model.SocialSignInPlatform
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import com.teamoffroad.feature.auth.presentation.model.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val googleSignInClient: GoogleSignInClient,
    private val authUseCase: AuthUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
) : ViewModel() {
    private val _authUiState: MutableStateFlow<AuthUiState> =
        MutableStateFlow(AuthUiState(empty = true))
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    private val _sideEffect: Channel<Boolean> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    fun startKakaoSignIn() {
        viewModelScope.launch {
            _authUiState.value = _authUiState.value.copy(
                kakaoSignIn = true
            )
        }
    }

    fun performGoogleSignIn(result: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            runCatching {
                result.getResult(ApiException::class.java)
            }.onSuccess { account ->
                signIn(
                    SocialSignInPlatform.GOOGLE.name,
                    null,
                    account.serverAuthCode.toString(),
                )
            }.onFailure {
            }
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
                _authUiState.value = _authUiState.value.copy(
                    signInSuccess = true,
                    alreadyExist = signInInfo.isAlreadyExist
                )
            }.onFailure {
            }
        }
    }

    fun checkAutoSignIn() {
        viewModelScope.launch {
            getAutoSignInUseCase().collect { isAutoSignIn ->
                _authUiState.value = _authUiState.value.copy(
                    isAutoSignIn = isAutoSignIn
                )
            }
        }
    }

    fun updateSignInResult() {
        viewModelScope.launch {
            _sideEffect.send(true)
        }
    }

    fun initState() {
        _authUiState.value = authUiState.value.copy(
            empty = true,
            signInSuccess = false,
            alreadyExist = false,
            kakaoSignIn = false,
            isAutoSignIn = false
        )
    }
}
