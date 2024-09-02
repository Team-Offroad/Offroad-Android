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
        val response = userService.getCharacters()

        val gainedCharacters = response.data?.isGainedCharacters?.map { it.toDomain(isGained = true) }.orEmpty()
        val notGainedCharacters = response.data?.isNotGainedCharacters?.map { it.toDomain(isGained = false) }.orEmpty()

        return gainedCharacters + notGainedCharacters
    }
}
