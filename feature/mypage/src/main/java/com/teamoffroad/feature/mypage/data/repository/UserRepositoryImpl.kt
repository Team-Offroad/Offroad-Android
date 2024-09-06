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
        val response = userService.getCharacters().data

        val gainedCharacters = response?.gainedCharacters?.map {
            it.toDomain(
                isGained = true,
                representativeCharacterId = response.representativeCharacterId,
            )
        }.orEmpty()

        val notGainedCharacters = response?.notGainedCharacters?.map {
            it.toDomain(isGained = false)
        }.orEmpty()

        return gainedCharacters + notGainedCharacters
    }
}
