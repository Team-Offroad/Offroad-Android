package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.mapper.toData
import com.teamoffroad.feature.mypage.data.mapper.toDomain
import com.teamoffroad.feature.mypage.data.remote.request.DeleteUserInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.service.UserService
import com.teamoffroad.feature.mypage.domain.model.Character
import com.teamoffroad.feature.mypage.domain.model.MyPageUser
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

    override suspend fun fetchMyPage(): MyPageUser {
        val myPageResponse = userService.getMyPageUser()
        val domainMyPageUser = myPageResponse.data?.toData()?.toDomain()
        return domainMyPageUser ?: MyPageUser("", "", 0, 0, 0, "")
    }

    override suspend fun saveUserInfo(deleteCode: String): Result<Unit> {
        val deleteUserInfo =
            runCatching { userService.deleteUserInfo(DeleteUserInfoRequestDto(deleteCode)) }
        deleteUserInfo.onSuccess {
        }
        deleteUserInfo.onFailure {
        }
        return Result.failure(UnReachableException("unreachable code"))
    }

    override suspend fun deleteUserInfo(deleteCode: String): Result<Unit> {
        val deleteUserInfo =
            runCatching { userService.deleteUserInfo(DeleteUserInfoRequestDto(deleteCode)) }
        deleteUserInfo.onSuccess {
        }
        deleteUserInfo.onFailure {
        }
        return Result.failure(UnReachableException("unreachable code"))
    }
}
