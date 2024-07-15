package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.mapper.toData
import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.service.EmblemService
import com.teamoffroad.feature.home.domain.model.Emblems
import com.teamoffroad.feature.home.domain.repository.EmblemRepository
import javax.inject.Inject

class EmblemRepositoryImpl @Inject constructor(
    private val emblemService: EmblemService
) : EmblemRepository {
    override suspend fun getEmblems(token: String): Emblems {
        val response = emblemService.getEmblemsList(token)
        val emblemsEntity = response.data?.emblems?.map { it.toData() } ?: emptyList()
        val domainEmblems = emblemsEntity.map { it.toDomain() }
        return Emblems(domainEmblems)
    }
}
