package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.CharacterDetail
import com.teamoffroad.feature.mypage.domain.repository.CharacterRepository

class GetCharacterDetailUseCase(
    private val characterRepository: CharacterRepository,
) {

    suspend operator fun invoke(characterId: Int): CharacterDetail {
        return characterRepository.fetchCharacterDetail(characterId)
    }
}