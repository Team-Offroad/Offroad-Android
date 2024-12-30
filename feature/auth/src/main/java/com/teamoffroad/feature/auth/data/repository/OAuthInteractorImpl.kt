package com.teamoffroad.feature.auth.data.repository

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.feature.auth.domain.repository.OAuthInteractor
import com.teamoffroad.offroad.feature.auth.BuildConfig
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class OAuthInteractorImpl @Inject constructor(
    private val client: UserApiClient,
    private val credentialManager: CredentialManager,
    @ActivityContext private val context: Context,
) : OAuthInteractor {

    override suspend fun signInKakao(): Result<Token> =
        suspendCancellableCoroutine { continuation ->
            when (client.isKakaoTalkLoginAvailable(context)) {
                true -> client.loginWithKakaoTalk(context) { token, error ->
                    when {
                        error is ClientError && error.reason == ClientErrorCause.Cancelled -> return@loginWithKakaoTalk
                        error != null -> handleKakaoSignIn(continuation)
                        token != null -> continuation.resume(
                            Result.success(
                                Token(token.accessToken, token.refreshToken)
                            )
                        )
                    }
                }

                false -> handleKakaoSignIn(continuation)
            }
        }

    private fun handleKakaoSignIn(continuation: CancellableContinuation<Result<Token>>) {
        client.loginWithKakaoAccount(context) { token, error ->
            when {
                error != null -> continuation.resume(Result.failure(error))
                token != null -> continuation.resume(
                    Result.success(Token(token.accessToken, token.refreshToken))
                )

                else -> continuation.resumeWithException(Throwable("Unreachable code"))
            }
        }
    }

    override suspend fun signInGoogle(): Result<String> =
        withContext(Dispatchers.IO) {
            try {
                val hashedNonce = getHashedNonce()
                val googleIdOption = createGoogleIdOption(hashedNonce)
                val request = createCredentialRequest(googleIdOption)

                val result = credentialManager.getCredential(context = context, request = request)
                val credential = result.credential

                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val googleIdToken = googleIdTokenCredential.idToken
                Result.success(googleIdToken)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    private fun getHashedNonce(): String {
        val bytes = UUID.randomUUID().toString().toByteArray()
        val digest = MessageDigest.getInstance("SHA-256").digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    private fun createGoogleIdOption(hashedNonce: String): GetGoogleIdOption {
        return GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .setNonce(hashedNonce)
            .build()
    }

    private fun createCredentialRequest(googleIdOption: GetGoogleIdOption): GetCredentialRequest {
        return GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }
}
