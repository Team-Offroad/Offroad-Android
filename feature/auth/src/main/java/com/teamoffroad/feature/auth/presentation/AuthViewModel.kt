package com.teamoffroad.feature.auth.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    val googleSignInClient: GoogleSignInClient,
) : ViewModel() {

    private val _user = MutableLiveData<GoogleSignInAccount?>()
    val user: LiveData<GoogleSignInAccount?> = _user

    fun handleSignInResult(result: Task<GoogleSignInAccount>) {
        try {
            val account = result.getResult(ApiException::class.java)
            _user.value = account
            Log.e("123123", "signInResult:success account=$account")
        } catch (e: ApiException) {
            Log.w("123123", "signInResult:failed code=" + e.statusCode)
            _user.value = null
        }
    }

    fun signOut() {
        googleSignInClient.signOut().addOnCompleteListener {
            _user.value = null
        }
    }
}
