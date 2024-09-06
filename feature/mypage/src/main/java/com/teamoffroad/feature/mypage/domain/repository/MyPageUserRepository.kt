package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.MyPageUser

interface MyPageUserRepository {
    suspend fun fetchMyPage(): MyPageUser
    suspend fun patchMarketingInfo(): Result<Unit>
    suspend fun deleteUserInfo(deleteCode: String): Result<Unit>
}