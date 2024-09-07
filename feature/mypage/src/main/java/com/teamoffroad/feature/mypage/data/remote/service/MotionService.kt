package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.MotionsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MotionService {
    @GET("motions/{characterId}")
    suspend fun getCharacterMotions(
        @Path("characterId") characterId: Int,
    ): BaseResponse<MotionsResponseDto>
}
