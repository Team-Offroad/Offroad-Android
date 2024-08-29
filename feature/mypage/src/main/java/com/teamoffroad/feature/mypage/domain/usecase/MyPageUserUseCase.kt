package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository

class MyPageUserUseCase(
    private val myPageUserRepository: MyPageUserRepository
) {
    suspend fun getMyPageUser(): MyPageUser {
        return myPageUserRepository.getUsersMyPage()
    }
}