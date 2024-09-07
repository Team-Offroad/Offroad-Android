package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class UpdateCharacterUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(characterId: Int): String {
        return authRepository.saveCharacter(characterId)
    }
}
