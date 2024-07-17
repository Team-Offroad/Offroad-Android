package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.model.Character
import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class GetCharacterListUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(): List<Character> {
        return authRepository.getCharacters()
    }
}
