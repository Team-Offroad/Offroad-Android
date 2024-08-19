package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.UserCouponsResponseDto
import retrofit2.http.GET

interface UserCouponsService {
    @GET("/users/coupons")
    suspend fun getCoupons(): BaseResponse<UserCouponsResponseDto>
}