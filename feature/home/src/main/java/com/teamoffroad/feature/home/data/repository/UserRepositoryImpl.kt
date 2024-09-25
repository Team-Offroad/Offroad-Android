package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.mapper.toData
import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.service.UserService
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformation
import com.teamoffroad.feature.home.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {
    override suspend fun getEmblems(): List<Emblem> {
        val response = userService.getEmblemsList()
        val emblemsEntity = response.data?.emblems?.map { it.toData() } ?: emptyList()
        val domainEmblems = emblemsEntity.map { it.toDomain() }
        return domainEmblems
    }

    override suspend fun getUserQuests(): UserQuests {
        val response = userService.getUsersQuests()
        val questRecentEntity = response.data?.recentResponseDto?.toData()
        val questAlmostEntity = response.data?.almostResponseDto?.toData()
        val domainRecentEntity = questRecentEntity?.toDomain()
        val domainAlmostEntity = questAlmostEntity?.toDomain()

        return UserQuests(
            userRecent = domainRecentEntity ?: UserQuests.UserRecent("", 0, 0),
            userAlmost = domainAlmostEntity ?: UserQuests.UserAlmost("", 0, 0)
        )
    }

    override suspend fun getUsersAdventuresInformation(category: String): UsersAdventuresInformation {
        val response = userService.getAdventuresInformation(category)
        val adventuresInformation = response.data?.toData()
        val domainAdventuresInformation = adventuresInformation?.toDomain()
        return domainAdventuresInformation ?: UsersAdventuresInformation("", "", "", "", "", "")
    }

    override suspend fun patchEmblem(emblemCode: String) {
        userService.patchEmblem(emblemCode)
    }
}
