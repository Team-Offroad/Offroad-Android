package com.teamoffroad.feature.auth.data.repository

import android.content.Context
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.feature.auth.domain.repository.OAuthInteractor
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class OAuthInteractorImpl @Inject constructor(
    private val client: UserApiClient,
    @ActivityContext private val context: Context,
) : OAuthInteractor {

    override suspend fun signInKakao(): Result<Token> =
        suspendCancellableCoroutine {
            when (client.isKakaoTalkLoginAvailable(context)) {
                true -> {
                    client.loginWithKakaoTalk(context) { token, error ->
                        if (error != null) {
                            if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                                return@loginWithKakaoTalk
                            }
                            client.loginWithKakaoAccount(context) { accountToken, accountError ->
                                if (accountError != null) {
                                    it.resume(Result.failure(accountError))
                                    return@loginWithKakaoAccount
                                }
                                if (accountToken != null) {
                                    it.resume(
                                        Result.success(
                                            Token(
                                                accountToken.accessToken,
                                                accountToken.refreshToken
                                            )
                                        )
                                    )
                                    return@loginWithKakaoAccount
                                }
                            }
                        } else if (token != null) {
                            it.resume(Result.success(Token(token.accessToken, token.refreshToken)))
                            return@loginWithKakaoTalk
                        }
                    }
                }

                false -> client.loginWithKakaoAccount(context) { token, error ->
                    if (error != null) {
                        it.resume(Result.failure(error))
                        return@loginWithKakaoAccount
                    }
                    if (token != null) {
                        it.resume(Result.success(Token(token.accessToken, token.refreshToken)))
                        return@loginWithKakaoAccount
                    }
                    it.resumeWithException(Throwable("Unreachable code"))
                }
            }
        }

    override suspend fun signInGoogle(): Result<Token> {
        TODO("Not yet implemented")
    }
}