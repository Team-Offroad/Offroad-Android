package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.MyPageUserRepository

class DeleteUserInfoUseCase(
    private val userRepository: MyPageUserRepository
) {
    suspend operator fun invoke(deleteCode: String): Result<Unit> {
        return userRepository.deleteUserInfo(deleteCode)
    }
}