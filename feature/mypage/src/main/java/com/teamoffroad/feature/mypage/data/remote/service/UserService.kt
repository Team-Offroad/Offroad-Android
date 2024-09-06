package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.MyPageUserResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.GainedEmblemsResponseDto
import retrofit2.http.GET

interface UserService {
    @GET("users/me")
    suspend fun getMyPageUser(): BaseResponse<MyPageUserResponseDto>
    @GET("emblems")
    suspend fun getGainedEmblems(): BaseResponse<GainedEmblemsResponseDto>
}