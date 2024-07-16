package com.teamoffroad.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val googleSignInClient: GoogleSignInClient,
) : ViewModel() {

    private val _user = MutableStateFlow<GoogleSignInAccount?>(null)
    val user: StateFlow<GoogleSignInAccount?> = _user

    fun handleSignInResult(result: Task<GoogleSignInAccount>) {
        viewModelScope.launch {
            runCatching {
                result.getResult(ApiException::class.java)
            }.onSuccess { account ->
                _user.value = account
            }.onFailure {
                Log.e("123123", it.message.toString())
            }
        }
    }

    // TODO: 추후 마이페이지에서 사용
    fun signOut() {
        googleSignInClient.signOut().addOnCompleteListener {
            _user.value = null
        }
    }
}
