package com.teamoffroad.feature.mypage.domain.repository

import com.teamoffroad.feature.mypage.domain.model.CharacterDetail

interface CharacterRepository {

    suspend fun fetchCharacterDetail(characterId: Int): CharacterDetail
}
