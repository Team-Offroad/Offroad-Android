package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.request.DeleteUserInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.request.MarketingInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.DeleteUserInfoResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.GainedEmblemsResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.MarketingInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @GET("emblems")
    suspend fun getGainedEmblems(): BaseResponse<GainedEmblemsResponseDto>

    @PATCH("users/agree")
    suspend fun patchMarketingInfo(
        @Body marketing: MarketingInfoRequestDto,
    ): BaseResponse<MarketingInfoResponseDto>

    @POST("users/delete")
    suspend fun deleteUserInfo(
        @Body deleteCode: DeleteUserInfoRequestDto,
    ): BaseResponse<DeleteUserInfoResponseDto>
}