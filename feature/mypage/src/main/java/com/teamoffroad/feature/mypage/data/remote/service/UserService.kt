package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.GainedEmblemsResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.MarketingInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {
    @GET("emblems")
    suspend fun getGainedEmblems(): BaseResponse<GainedEmblemsResponseDto>

    @PATCH("agree")
    suspend fun patchMarketingInfo(
        @Body marketing: Boolean,
    ): BaseResponse<MarketingInfoResponseDto>
}