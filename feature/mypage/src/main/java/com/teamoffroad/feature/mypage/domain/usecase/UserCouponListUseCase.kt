package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository

class UserCouponListUseCase(
    private val userCouponRepository: UserCouponRepository,
) {
    suspend fun fetchUserAvailableCoupons(isUsed: Boolean, size: Int, cursor: Int): UserAvailableCoupons {
        return userCouponRepository.fetchUserAvailableCoupons(isUsed, size, cursor)
    }

    suspend fun fetchUserUsedCoupons(isUsed: Boolean, size: Int, cursor: Int): UserUsedCoupons {
        return userCouponRepository.fetchUserUsedCoupons(isUsed, size, cursor)
    }

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponRepository.saveUseCoupon(coupon);
    }
}