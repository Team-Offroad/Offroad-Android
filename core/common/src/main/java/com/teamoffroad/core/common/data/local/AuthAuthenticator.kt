package com.teamoffroad.core.common.data.local

import android.content.Context
import android.util.Log
import com.jakewharton.processphoenix.ProcessPhoenix
import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.core.common.intentProvider.IntentProvider
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
    private val refreshTokenUseCase: TokenService,
    private val setAutoSignInUseCase: SetAutoSignInUseCase,
    @ApplicationContext private val context: Context,
    private val intentProvider: IntentProvider
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        val tokenResponse = runCatching {
                runBlocking {
                    refreshTokenUseCase.refreshAccessToken("Bearer ${tokenPreferencesDataSource.refreshToken.first()}")
                }
            }.onSuccess {

            Log.d("asdasd", "재발급성공")
                runBlocking {
                    tokenPreferencesDataSource.apply {
                        if (it != null) {
                            setAccessToken(it.data?.accessToken ?: return@runBlocking)
                            setRefreshToken(it.data?.refreshToken ?: return@runBlocking)
                        }
                    }
                }
            }.onFailure {

            Log.d("asdasd", "재발급실패")
                runBlocking {
                    setAutoSignInUseCase.invoke(false)
                }
                ProcessPhoenix.triggerRebirth(context, intentProvider.getIntent())
            }.getOrThrow()

        return response.request.newBuilder()
            .header(AUTHORIZATION, "Bearer ${tokenResponse?.data?.accessToken}")
            .build()
        Log.d("asdasd", response.message)
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
    }
}
