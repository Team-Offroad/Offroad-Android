package com.teamoffroad.core.common.data.local

import android.content.Context
import com.jakewharton.processphoenix.ProcessPhoenix
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.preferences.TokenPreferences
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.core.common.util.IntentProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator
    @Inject
    constructor(
        private val tokenPreferences: TokenPreferences,
        private val refreshTokenUseCase: TokenService,
        private val setAutoSignInUseCase: SetAutoSignInUseCase,
        @ApplicationContext private val context: Context,
        private val intentProvider: IntentProvider,
    ) : Authenticator {
        override fun authenticate(
            route: Route?,
            response: Response,
        ): Request? {
            val tokenResponse =
                runCatching {
                    runBlocking {
                        refreshTokenUseCase.refreshAccessToken("Bearer ${tokenPreferences.refreshToken.first()}")
                    }
                }.onSuccess {
                    runBlocking {
                        tokenPreferences.apply {
                            setAccessToken(it.data?.accessToken ?: return@runBlocking)
                            setRefreshToken(it.data.refreshToken ?: return@runBlocking)
                        }
                    }
                }.onFailure {
                    runBlocking {
                        setAutoSignInUseCase.invoke(false)
                    }
                    ProcessPhoenix.triggerRebirth(context, intentProvider.getIntent())
                }.getOrThrow()

            return response.request
                .newBuilder()
                .header(AUTHORIZATION, "Bearer ${tokenResponse.data?.accessToken}")
                .build()
        }

        companion object {
            private const val AUTHORIZATION = "Authorization"
        }
    }
