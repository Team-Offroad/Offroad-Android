package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.Character
import com.teamoffroad.feature.mypage.domain.model.MyPageUser

interface UserRepository {

    suspend fun fetchCharacters(): List<Character>

    suspend fun fetchMyPage(): MyPageUser

    suspend fun saveMarketingInfo(): Result<Unit>

    suspend fun saveUserInfo(deleteCode: String): Result<Unit>
}