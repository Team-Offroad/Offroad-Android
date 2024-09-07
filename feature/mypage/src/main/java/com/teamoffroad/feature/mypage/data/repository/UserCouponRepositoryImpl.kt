package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.UserCouponService
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCouponList
import com.teamoffroad.feature.mypage.domain.repository.UserCouponRepository
import javax.inject.Inject

class UserCouponRepositoryImpl @Inject constructor(
    private val userCouponService: UserCouponService,
) : UserCouponRepository {
    override suspend fun fetchUserCoupons(): UserCouponList {
        val response = userCouponService.getCoupons()
        val userAvailableCouponsEntity =
            response.data?.availableCouponsDto?.map { it.toData() } ?: emptyList()
        val userUsedCouponsEntity =
            response.data?.usedCouponsDto?.map { it.toData() } ?: emptyList()

        val domainUserAvailableCouponsEntity = userAvailableCouponsEntity.map { it.toDomain() }
        val domainUserUsedCouponsEntity = userUsedCouponsEntity.map { it.toDomain() }

        return UserCouponList(
            availableCoupons = domainUserAvailableCouponsEntity,
            usedCoupons = domainUserUsedCouponsEntity
        )
    }

    override suspend fun saveUseCoupon(coupon: UseCoupon): Boolean {
        return userCouponService.saveCoupons(coupon.toData())?.data?.success ?: false
    }
}