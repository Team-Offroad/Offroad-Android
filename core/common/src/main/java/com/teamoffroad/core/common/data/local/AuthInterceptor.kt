package com.teamoffroad.core.common.data.local

import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val authAuthenticator: AuthAuthenticator,
    private val tokenPreferencesDataSource: TokenPreferencesDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = runBlocking {
            tokenPreferencesDataSource.accessToken.first()
        }

        val requestBuilder = chain.request().newBuilder()
        token.let {
            requestBuilder.header(AUTHORIZATION, "Bearer $it")
        }

        val request = requestBuilder.build()
        var response = chain.proceed(request)

        if (response.code == HTTP_UNAUTHORIZED) {
            runBlocking {
                val authenticator = Authenticator { route, response ->
                    authAuthenticator.authenticate(route, response)
                }
                val newRequest = authenticator.authenticate(null, response)
                if (newRequest != null) {
                    response = chain.proceed(newRequest)
                }
            }
        }

        if (response.code == HTTP_OK) {
            val newAccessToken: String? = response.header(AUTHORIZATION, null)
            newAccessToken?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    val existedAccessToken = tokenPreferencesDataSource.accessToken.first()
                    if (existedAccessToken != it) {
                        tokenPreferencesDataSource.setAccessToken(it)
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
