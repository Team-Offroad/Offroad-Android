package com.teamoffroad.core.common.data.local

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import dagger.Lazy
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenPreferencesDataSource: TokenPreferencesDataSource,
    private val refreshTokenUseCase: Lazy<RefreshTokenUseCase>,
    @ApplicationContext private val context: Context,
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = runBlocking {
            tokenPreferencesDataSource.refreshToken.first()
        }

        val tokenResponse = runCatching {
            runBlocking {
                refreshTokenUseCase.get().invoke("Bearer $refreshToken")
            }
        }
            .onSuccess {
                runBlocking {
                    tokenPreferencesDataSource.apply {
                        if (it != null) {
                            setAccessToken(it.accessToken)
                            setRefreshToken(it.refreshToken)
                        }
                    }
                }
            }
            .onFailure {
                runBlocking {
                    //auth signin false
                    ProcessPhoenix.triggerRebirth(context)
                }
            }

        return response.request.newBuilder()
            .header(AUTHORIZATION, "Bearer ${tokenResponse.getOrNull()?.accessToken}")
            .build()
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
