package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.service.UserService
import com.teamoffroad.feature.mypage.domain.model.Character
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
) : UserRepository {

    override suspend fun fetchCharacters(): List<Character> {
        val characters = userService.getCharacters().data ?: return emptyList()

        val representativeCharacter = characters.gainedCharacters
            .find { it.characterId == characters.representativeCharacterId }?.toDomain(
                isGained = true,
                isRepresentative = true,
            ) ?: return emptyList()

        val gainedCharacters = characters.gainedCharacters
            .filter { it.characterId != characters.representativeCharacterId }
            .map {
                it.toDomain(isGained = true)
            }

        val notGainedCharacters = characters.notGainedCharacters
            .map {
                it.toDomain(isGained = false)
            }

        return listOf(representativeCharacter) + gainedCharacters + notGainedCharacters
    }
}
