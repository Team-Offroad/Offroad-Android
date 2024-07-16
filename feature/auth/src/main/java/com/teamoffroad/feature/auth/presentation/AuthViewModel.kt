package com.teamoffroad.feature.auth.presentation

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {

    fun startGoogleSignIn(
        context: Context,
        request: GetCredentialRequest,
        onSuccess: (GoogleIdTokenCredential) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        val credentialManager: CredentialManager by lazy {
            CredentialManager.create(context)
        }

        viewModelScope.launch {
            try {
                val result: GetCredentialResponse = credentialManager.getCredential(context, request)
                handleSignIn(result, onSuccess)
            } catch (e: GetCredentialException) {
                onFailure(e)
            }
        }
    }

    private fun handleSignIn(result: GetCredentialResponse, onSuccess: (GoogleIdTokenCredential) -> Unit) {
        val credential = result.credential
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                onSuccess(googleIdTokenCredential)
            } catch (e: GoogleIdTokenParsingException) {
                Log.e("1231232", "Received an invalid google id token response", e)
            }
        } else {
            Log.e("1231232", "Unexpected type of credential")
        }
    }
}