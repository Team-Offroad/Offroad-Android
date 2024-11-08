package com.teamoffroad.feature.auth.data.repository

import android.content.Context
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.feature.auth.domain.repository.OAuthInteractor
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class OAuthInteractorImpl @Inject constructor(
    private val client: UserApiClient,
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

    override suspend fun signInGoogle(): Result<Token> {
        TODO("Not yet implemented")
    }
}