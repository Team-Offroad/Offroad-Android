package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCouponList
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository

class UserCouponListUseCase(
    private val userCouponRepository: UserCouponRepository
) {
    suspend fun fetchUserCoupons(): UserCouponList {
        return userCouponRepository.fetchUserCoupons()
    }

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponRepository.saveUseCoupon(coupon);
    }
}