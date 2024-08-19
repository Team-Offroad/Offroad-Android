package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.UserCouponsService
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.domain.repository.UserCouponsRepository
import javax.inject.Inject

class UserCouponsRepositoryImpl @Inject constructor(
    private val userCouponsService: UserCouponsService
) : UserCouponsRepository {
    override suspend fun getUserCoupons(): UserCoupons {
        val response = userCouponsService.getCoupons()
        val userAvailableCouponsEntity =
            response.data?.availableCouponsDto?.map { it.toData() } ?: emptyList()
        val userUsedCouponsEntity =
            response.data?.usedCouponsDto?.map { it.toData() } ?: emptyList()

        val domainUserAvailableCouponsEntity = userAvailableCouponsEntity.map { it.toDomain() }
        val domainUserUsedCouponsEntity = userUsedCouponsEntity.map { it.toDomain() }

        return UserCoupons(
            availableCoupons = domainUserAvailableCouponsEntity,
            usedCoupons = domainUserUsedCouponsEntity
        )
    }
}