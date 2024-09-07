package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCouponList

interface UserCouponRepository {
    suspend fun fetchUserCoupons(): UserCouponList

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean
}