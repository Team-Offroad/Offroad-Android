package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponsRepository

class UserCouponsUseCase(
    private val userCouponsRepository: UserCouponsRepository
) {
    suspend fun getUserCoupons(): UserCoupons {
        return userCouponsRepository.fetchUserCoupons()
    }
}