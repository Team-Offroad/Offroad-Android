package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.UserCouponService
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import javax.inject.Inject

class UserCouponRepositoryImpl @Inject constructor(
    private val userCouponService: UserCouponService,
) : UserCouponRepository {
    override suspend fun fetchUserAvailableCoupons(
        isUsed: Boolean,
        size: Int,
        cursor: Int
    ): UserAvailableCoupons {
        val response = userCouponService.getAvailableCoupons(isUsed, size, cursor).data
        return response?.toData()?.toDomain() ?: UserAvailableCoupons(emptyList(), 0, 0)
    }

    override suspend fun fetchUserUsedCoupons(
        isUsed: Boolean,
        size: Int,
        cursor: Int
    ): UserUsedCoupons {
        val response = userCouponService.getUsedCoupons(isUsed, size, cursor).data
        return response?.toData()?.toDomain() ?: UserUsedCoupons(emptyList(), 0, 0)
    }

    override suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponService.saveCoupons(coupon.toData()).data?.success ?: false
    }
}