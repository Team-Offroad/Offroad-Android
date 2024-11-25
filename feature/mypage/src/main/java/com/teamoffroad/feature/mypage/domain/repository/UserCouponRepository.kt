package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons

interface UserCouponRepository {
    suspend fun fetchUserAvailableCoupons(
        isUsed: Boolean,
        size: Int,
        cursor: Int
    ): UserAvailableCoupons

    suspend fun fetchUserUsedCoupons(
        isUsed: Boolean,
        size: Int,
        cursor: Int
    ): UserUsedCoupons

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean
}