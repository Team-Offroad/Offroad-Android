package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.CharacterService
import com.teamoffroad.feature.mypage.domain.model.CharacterDetail
import com.teamoffroad.feature.mypage.domain.repository.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val characterService: CharacterService,
) : CharacterRepository {

    override suspend fun fetchCharacterDetail(characterId: Int): CharacterDetail {
        return characterService.getCharacterDetail(characterId).data?.toDomain() ?: CharacterDetail()
    }
}
