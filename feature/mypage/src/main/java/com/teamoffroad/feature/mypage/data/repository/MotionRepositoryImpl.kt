package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.MotionService
import com.teamoffroad.feature.mypage.domain.model.CharacterMotion
import com.teamoffroad.feature.mypage.domain.repository.MotionRepository
import javax.inject.Inject

class MotionRepositoryImpl @Inject constructor(
    private val motionService: MotionService,
) : MotionRepository {

    override suspend fun fetchCharacterMotions(characterId: Int): List<CharacterMotion> {
        val motions = motionService.getCharacterMotions(characterId).data ?: return emptyList()

        val gainedCharacterMotions = motions.gainedCharacterMotions.map { it.toDomain() }
        val notGainedCharacterMotions = motions.notGainedCharacterMotions.map { it.toDomain() }

        return gainedCharacterMotions + notGainedCharacterMotions
    }
}
