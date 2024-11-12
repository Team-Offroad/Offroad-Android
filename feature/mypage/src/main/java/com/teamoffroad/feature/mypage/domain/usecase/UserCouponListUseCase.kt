package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository

class UserCouponListUseCase(
    private val userCouponRepository: UserCouponRepository,
) {
    suspend fun fetchUserCoupons(isUsed: Boolean, size: Int, cursor: Int): UserCoupons {
        return userCouponRepository.fetchUserCoupons(isUsed, size, cursor)
    }

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponRepository.saveUseCoupon(coupon);
    }
}