package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.model.CharacterMotion
import com.teamoffroad.feature.mypage.domain.repository.MotionRepository

class GetCharacterMotionListUseCase(
    private val motionRepository: MotionRepository,
) {

    suspend operator fun invoke(characterId: Int): List<CharacterMotion> {
        return motionRepository.fetchCharacterMotions(characterId)
    }
}