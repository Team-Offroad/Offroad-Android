package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.UserProfile
import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class PatchUserProfileUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        nickname: String,
        birthDate: String?,
        gender: String?,
        characterId: Int,
    ): String {
        return authRepository.saveUserProfile(
            userProfile = UserProfile(
                nickname = nickname,
                year = birthDate?.split(",")?.get(0)?.toInt(),
                month = birthDate?.split(",")?.get(1)?.toInt(),
                day = birthDate?.split(",")?.get(2)?.toInt(),
                gender = gender,
                characterId = characterId,
            )
        )
    }
}