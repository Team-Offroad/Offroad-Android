package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.UserRepository

class DeleteUserInfoUseCase(
    private val userRepository: UserRepository,
) {
    suspend operator fun invoke(deleteCode: String): Result<Unit> {
        return userRepository.saveUserInfo(deleteCode)
    }
}