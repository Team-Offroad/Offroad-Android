package com.teamoffroad.core.common.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.core.common.data.remote.response.TokenResponseDto
import retrofit2.http.Header
import retrofit2.http.POST

interface TokenService {

    @POST("auth/refresh")
    suspend fun refreshAccessToken(
        @Header("Authorization") refreshToken: String,
    ): BaseResponse<TokenResponseDto>
}
