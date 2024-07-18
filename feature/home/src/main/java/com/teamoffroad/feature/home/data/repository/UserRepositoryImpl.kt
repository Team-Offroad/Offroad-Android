package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.mapper.toData
import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.service.UserService
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.model.UsersAdventuresInformations
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

    override suspend fun getUsersAdventuresInformations(category: String): UsersAdventuresInformations {
        val response = userService.getAdventuresInformations(category)
        val adventuresInformations = response.data?.toData()
        val domainAdventuresInformations = adventuresInformations?.toDomain()
        return domainAdventuresInformations ?: UsersAdventuresInformations("", "", "", "", "")
    }

    override suspend fun patchEmblem(emblemCode: String) {
        userService.patchEmblem(emblemCode)
    }
}
