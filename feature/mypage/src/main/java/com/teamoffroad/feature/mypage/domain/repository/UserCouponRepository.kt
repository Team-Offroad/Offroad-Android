package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons

interface UserCouponRepository {
    suspend fun fetchUserCoupons(
        isUsed: Boolean,
        size: Int,
        cursor: Int
    ): UserCoupons

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean
}