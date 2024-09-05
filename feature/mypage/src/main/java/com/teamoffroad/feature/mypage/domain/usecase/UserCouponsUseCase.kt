package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponsRepository

class UserCouponsUseCase(
    private val userCouponsRepository: UserCouponsRepository
) {
    suspend fun fetchUserCoupons(): UserCoupons {
        return userCouponsRepository.fetchUserCoupons()
    }

    suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponsRepository.saveUseCoupon(coupon);
    }
}