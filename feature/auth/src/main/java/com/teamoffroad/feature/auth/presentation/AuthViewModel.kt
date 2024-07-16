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
import com.teamoffroad.feature.auth.domain.model.SignInInfo
import com.teamoffroad.feature.auth.domain.model.UserToken
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import com.teamoffroad.feature.auth.presentation.model.SocialSignInPlatform
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val googleSignInClient: GoogleSignInClient,
    private val authUseCase: AuthUseCase,
    private val saveAccessTokenUseCase: SaveAccessTokenUseCase,
    private val saveRefreshTokenUseCase: SaveRefreshTokenUseCase,
) : ViewModel() {

    private val _userToken = MutableStateFlow<UserToken>(UserToken("", ""))
    val userToken: StateFlow<UserToken> = _userToken

    private val _successSignIn = MutableStateFlow(false)
    val successSignIn: StateFlow<Boolean> = _successSignIn

    fun performGoogleSignIn(result: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            runCatching {
                result.getResult(ApiException::class.java)
            }.onSuccess { account ->
                signIn(
                    SignInInfo(
                        SocialSignInPlatform.GOOGLE.name,
                        null,
                        account.serverAuthCode.toString(),
                    )
                )
            }.onFailure {
                Log.e("123123", it.message.toString())
            }
        }
    }

    private suspend fun signIn(signInInfo: SignInInfo) {
        viewModelScope.launch {
            runCatching {
                authUseCase.invoke(signInInfo)
            }.onSuccess { userToken ->
                _userToken.value = userToken
                saveAccessTokenUseCase.invoke(userToken.accessToken)
                saveRefreshTokenUseCase.invoke(userToken.refreshToken)
                _successSignIn.value = true
            }.onFailure {
                Log.e("123123", it.message.toString())
            }
        }
    }

    // TODO: 추후 마이페이지에서 사용
    fun performSignOut() {
        googleSignInClient.signOut().addOnCompleteListener {
        }
    }
}
