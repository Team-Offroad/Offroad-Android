package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.request.UseCouponRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.UseCouponResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.UserCouponsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserCouponService {
    @GET("users/coupons")
    suspend fun getCoupons(
        @Query("isUsed") isUsed: Boolean,
        @Query("size") size: Int,
        @Query("cursor") cursor: Int
    ): BaseResponse<UserCouponsResponseDto>

    @POST("users/coupons")
    suspend fun saveCoupons(
        @Body request: UseCouponRequestDto,
    ): BaseResponse<UseCouponResponseDto>
}