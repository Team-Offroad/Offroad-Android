package com.teamoffroad.feature.main.data.repository

import com.teamoffroad.feature.home.data.mapper.toData
import com.teamoffroad.feature.home.data.mapper.toDomain
import com.teamoffroad.feature.home.data.remote.response.DummyUserResponseDto
import com.teamoffroad.feature.home.domain.model.DummyUser
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.main.data.mapper.toData
import com.teamoffroad.feature.main.data.mapper.toDomain
import com.teamoffroad.feature.main.data.remote.service.MinSupportedVersionService
import com.teamoffroad.feature.main.domain.model.MinSupportedVersion
import com.teamoffroad.feature.main.domain.repository.MinSupportedVersionRepository
import javax.inject.Inject

class MinSupportedVersionRepositoryImpl @Inject constructor(
    private val minSupportedVersionService: MinSupportedVersionService
) : MinSupportedVersionRepository {

    override suspend fun fetchMinSupportedVersion(): MinSupportedVersion {
        val response = minSupportedVersionService.getMinSupportedVersion()
        return response.toData().toDomain()
    }
}
