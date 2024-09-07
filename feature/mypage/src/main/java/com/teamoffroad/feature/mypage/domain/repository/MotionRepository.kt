package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.CharacterMotion

interface MotionRepository {

    suspend fun fetchCharacterMotions(characterId: Int): List<CharacterMotion>
}
