package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.MyPageUser

interface MyPageUserRepository {
    suspend fun fetchMyPage(): MyPageUser
}