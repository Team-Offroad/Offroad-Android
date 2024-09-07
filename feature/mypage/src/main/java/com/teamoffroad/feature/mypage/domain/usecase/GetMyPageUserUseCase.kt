package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.MyPageUser
import com.teamoffroad.feature.mypage.domain.repository.UserRepository

class GetMyPageUserUseCase(
    private val userRepository: UserRepository,
) {
    suspend fun getMyPageUser(): MyPageUser {
        return userRepository.fetchMyPage()
    }
}