package com.teamoffroad.core.common.data.local

import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authAuthenticator: AuthAuthenticator,
    private val tokenPreferencesDataSource: TokenPreferencesDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken: String = runBlocking {
            tokenPreferencesDataSource.accessToken.first()
        }

        val requestBuilder = chain.request().newBuilder()
        val isRefreshTokenRequest = !chain.request().url.toString().endsWith("refresh")

        if (!isRefreshTokenRequest) {
            accessToken.let { token ->
                requestBuilder.header(AUTHORIZATION, "Bearer $token")
            }
        }

        val request = requestBuilder.build()
        var response = chain.proceed(request)

        if (response.code == HTTP_UNAUTHORIZED) {
            runBlocking {
                val newRequest = authAuthenticator.authenticate(null, response)

                if (newRequest != null) {
                    response.close()
                    response = chain.proceed(newRequest)
                }
            }
        }

        if (response.code == HTTP_OK) {
            val newAccessToken: String? = response.header(AUTHORIZATION, null)
            newAccessToken?.let { token ->
                CoroutineScope(Dispatchers.IO).launch {
                    val existedAccessToken = tokenPreferencesDataSource.accessToken.first()
                    if (existedAccessToken != token) {
                        tokenPreferencesDataSource.setAccessToken(token)
                    }
                }
            }
        }

        return response
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val HTTP_UNAUTHORIZED = 401
        private const val HTTP_OK = 200
    }
}
