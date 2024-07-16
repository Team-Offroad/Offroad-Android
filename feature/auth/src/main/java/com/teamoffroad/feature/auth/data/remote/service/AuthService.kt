package com.teamoffroad.feature.auth.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.response.SignInInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("oauth/login")
    suspend fun postSignInInfo(
        @Body request: SignInInfoRequestDto,
    ): BaseResponse<SignInInfoResponseDto>
}
