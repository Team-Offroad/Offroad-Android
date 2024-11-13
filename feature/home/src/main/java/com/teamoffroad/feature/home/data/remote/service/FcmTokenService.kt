package com.teamoffroad.feature.home.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.home.data.remote.request.FcmTokenRequestDto
import retrofit2.http.Body
import retrofit2.http.POST

interface FcmTokenService {

    @POST("fcm/token")
    suspend fun postFcmToken(
        @Body request: FcmTokenRequestDto,
    ): BaseResponse<Unit>
}