package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.mapper.toData
import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.service.EmblemService
import com.teamoffroad.feature.home.domain.model.Emblem
import com.teamoffroad.feature.home.domain.model.UserQuests
import com.teamoffroad.feature.home.domain.repository.EmblemRepository
import javax.inject.Inject

class EmblemRepositoryImpl @Inject constructor(
    private val emblemService: EmblemService
) : EmblemRepository {
    override suspend fun getEmblems(token: String): List<Emblem> {
        val response = emblemService.getEmblemsList(token)
        val emblemsEntity = response.data?.emblems?.map { it.toData() } ?: emptyList()
        val domainEmblems = emblemsEntity.map { it.toDomain() }
        return domainEmblems
    }

    override suspend fun getUserQuests(token: String): UserQuests {
        val response = emblemService.getUsersQuests(token)
        val questRecentEntity = response.data?.recentResponseDto?.toData()
        val questAlmostEntity = response.data?.almostResponseDto?.toData()
        val domainRecentEntity = questRecentEntity?.toDomain()
        val domainAlmostEntity = questAlmostEntity?.toDomain()
        return UserQuests(domainRecentEntity, domainAlmostEntity)
    }
}
