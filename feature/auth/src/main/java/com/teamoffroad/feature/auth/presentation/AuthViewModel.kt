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
import com.teamoffroad.feature.auth.domain.usecase.AuthUseCase
import com.teamoffroad.feature.auth.domain.usecase.ClearDataStoreUseCase
import com.teamoffroad.feature.auth.domain.usecase.GetAutoSignInUseCase
import com.teamoffroad.feature.auth.domain.usecase.SetAutoSignInUseCase
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
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
    private val getAutoSignInUseCase: GetAutoSignInUseCase,
    private val clearDataStoreUseCase: ClearDataStoreUseCase
) : ViewModel() {

    private val _successSignIn = MutableStateFlow(false)
    val successSignIn: StateFlow<Boolean> = _successSignIn

    private val _autoSignIn = MutableStateFlow(false)
    val autoSignIn: StateFlow<Boolean> = _autoSignIn

    private val _alreadyExist = MutableStateFlow(false)
    val alreadyExist: StateFlow<Boolean> = _alreadyExist

    fun clearDataStore() {
        viewModelScope.launch {
            runCatching { googleSignInClient.signOut() }
                .onSuccess { clearDataStoreUseCase.invoke() }
                .onFailure { it.message.toString() }
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
                Log.e("123123", it.message.toString())
            }
        }
    }

    private suspend fun signIn(socialPlatform: String, name: String?, code: String) {
        viewModelScope.launch {
            runCatching {
                authUseCase.invoke(socialPlatform, name, code)
            }.onSuccess { signInInfo ->
                saveAccessTokenUseCase.invoke(signInInfo.tokens.accessToken)
                saveRefreshTokenUseCase.invoke(signInInfo.tokens.refreshToken)
                setAutoSignInUseCase.invoke(true)
                _successSignIn.value = true
                _alreadyExist.value = signInInfo.isAlreadyExist
            }.onFailure {
                Log.e("123123", it.message.toString())
            }
        }
    }

    fun checkAutoSignIn() {
        viewModelScope.launch {
            getAutoSignInUseCase.invoke().collect { isAutoSignIn ->
                _autoSignIn.value = isAutoSignIn
            }
        }
    }

    // TODO: 추후 마이페이지에서 사용
    fun performSignOut() {
        googleSignInClient.signOut().addOnCompleteListener {
        }
    }
}
