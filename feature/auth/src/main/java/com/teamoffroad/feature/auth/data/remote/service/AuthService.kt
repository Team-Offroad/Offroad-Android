package com.teamoffroad.feature.auth.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.response.DuplicateNicknameResponseDto
import com.teamoffroad.feature.auth.data.remote.response.SignInInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("oauth/login")
    suspend fun postSignInInfo(
        @Body request: SignInInfoRequestDto,
    ): BaseResponse<SignInInfoResponseDto>

    @GET("users/nickname/check")
    suspend fun getDuplicateNickname(
        @Query("nickname") nickname: String
    ): BaseResponse<DuplicateNicknameResponseDto>
}
