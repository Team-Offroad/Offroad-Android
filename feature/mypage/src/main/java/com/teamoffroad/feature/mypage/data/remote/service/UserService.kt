package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.DeleteUserInfoResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.MyPageUserResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.GainedEmblemsResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.MarketingInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @GET("users/me")
    suspend fun getMyPageUser(): BaseResponse<MyPageUserResponseDto>

    @PATCH("agree")
    suspend fun patchMarketingInfo(
        @Body marketing: Boolean,
    ): BaseResponse<MarketingInfoResponseDto>

    @POST("delete")
    suspend fun deleteUserInfo(
        @Body deleteCode: String,
    ): BaseResponse<DeleteUserInfoResponseDto>
}