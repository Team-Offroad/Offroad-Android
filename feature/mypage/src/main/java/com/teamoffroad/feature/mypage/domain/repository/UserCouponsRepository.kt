package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.UserCoupons

interface UserCouponsRepository {
    suspend fun fetchUserCoupons(): UserCoupons
}