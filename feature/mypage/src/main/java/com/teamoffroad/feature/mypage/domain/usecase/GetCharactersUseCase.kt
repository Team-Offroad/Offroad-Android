package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.Character
import com.teamoffroad.feature.mypage.domain.repository.UserRepository

class GetCharactersUseCase(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(): List<Character> {
        return userRepository.fetchCharacters()
    }
}