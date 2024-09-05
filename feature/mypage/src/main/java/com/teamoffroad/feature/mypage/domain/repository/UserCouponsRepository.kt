package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons

interface UserCouponsRepository {
    suspend fun fetchUserCoupons(): UserCoupons

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean
}