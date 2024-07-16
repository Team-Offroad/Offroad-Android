package com.teamoffroad.core.common.data.local

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection.HTTP_OK
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String? = runBlocking {
            tokenManager.getAccessToken().first()
        }

        val requestBuilder = chain.request().newBuilder()
        token?.let {
            requestBuilder.header(AUTHORIZATION, "Bearer $it")
        }

        val request = requestBuilder.build()
        val response = chain.proceed(request)

        if (response.code == HTTP_OK) {
            val newAccessToken: String? = response.header(AUTHORIZATION, null)
            newAccessToken?.let {
                CoroutineScope(Dispatchers.IO).launch {
                    val existedAccessToken = tokenManager.getAccessToken().first()
                    if (existedAccessToken != it) {
                        tokenManager.saveAccessToken(it)
                    }
                }
            }
        }

        return response
    }

    private fun errorResponse(request: Request): Response = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_2)
        .code(NETWORK_ERROR_CODE)
        .message("Unauthorized token")
        .body("Unauthorized".toResponseBody("text/plain".toMediaType()))
        .build()

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val NETWORK_ERROR_CODE = 401
    }
}
