package com.teamoffroad.core.common.data.local

import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

class AuthAuthenticator @Inject constructor(
    private val tokenPreferencesDataSource: TokenPreferencesDataSource,
    private val refreshTokenUseCase: Provider<RefreshTokenUseCase>,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenPreferencesDataSource.refreshToken.first()
        }

        val tokenResponse = runBlocking {
            refreshTokenUseCase.get().invoke(refreshToken)
        } ?: return null

        runBlocking {
            tokenPreferencesDataSource.setAccessToken(tokenResponse.accessToken)
            tokenPreferencesDataSource.setRefreshToken(tokenResponse.refreshToken)
        }

        return response.request.newBuilder()
            .header(AUTHORIZATION, "Bearer ${tokenResponse.accessToken}")
            .build()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
