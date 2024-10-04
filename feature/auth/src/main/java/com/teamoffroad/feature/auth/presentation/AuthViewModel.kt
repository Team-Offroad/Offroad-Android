package com.teamoffroad.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.teamoffroad.core.common.domain.usecase.SaveAccessTokenUseCase
import com.teamoffroad.core.common.domain.usecase.SaveRefreshTokenUseCase
import com.teamoffroad.feature.auth.domain.model.SocialSignInPlatform
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.feature.auth.domain.usecase.UpdateAutoSignInUseCase
import com.teamoffroad.feature.auth.presentation.model.AuthUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val googleSignInClient: GoogleSignInClient,
    private val authUseCase: AuthUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
    private val updateAutoSignInUseCase: UpdateAutoSignInUseCase,
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
) : ViewModel() {
    private val _authUiState: MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState(empty = true))
    val authUiState: StateFlow<AuthUiState> = _authUiState.asStateFlow()

    init {
        checkAutoSignIn()
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
                Log.e("123123", it.message.toString())
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
                    authSignIn = true,
                    alreadyExist = signInInfo.isAlreadyExist
                )
            }.onFailure {
                Log.e("123123", it.message.toString())
            }
        }
    }

    private fun checkAutoSignIn() {

    }
}
