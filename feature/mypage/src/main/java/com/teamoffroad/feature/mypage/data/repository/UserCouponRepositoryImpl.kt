package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.UserCouponService
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import javax.inject.Inject

class UserCouponRepositoryImpl @Inject constructor(
    private val userCouponService: UserCouponService,
) : UserCouponRepository {
    override suspend fun fetchUserCoupons(
        isUsed: Boolean,
        size: Int,
        cursor: Int
    ): List<UserCoupons> {
        val coupons =
            userCouponService.getCoupons(isUsed, size, cursor).data?.coupons?.map { it.toDomain() }
                ?: return emptyList()
        return coupons
    }

    override suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponService.saveCoupons(coupon.toData())?.data?.success ?: false
    }
}