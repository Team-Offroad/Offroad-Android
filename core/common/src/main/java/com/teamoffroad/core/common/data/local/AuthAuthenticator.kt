package com.teamoffroad.core.common.data.local

import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val refreshTokenUseCase: RefreshTokenUseCase,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenManager.getRefreshToken().first()
        } ?: return null

        val tokenResponse = runBlocking {
            refreshTokenUseCase(refreshToken)
        } ?: return null

        runBlocking {
            tokenManager.saveAccessToken(tokenResponse.accessToken)
            tokenManager.saveRefreshToken(tokenResponse.refreshToken)
        }

        return response.request.newBuilder()
            .header(AUTHORIZATION, "Bearer ${tokenResponse.accessToken}")
            .build()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}

