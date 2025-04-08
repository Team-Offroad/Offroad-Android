package com.teamoffroad.core.common.data.repository

import com.teamoffroad.core.common.data.mapper.toData
import com.teamoffroad.core.common.data.mapper.toDomain
import com.teamoffroad.core.common.data.remote.service.MinSupportedVersionService
import com.teamoffroad.core.common.domain.model.MinSupportedVersion
import com.teamoffroad.core.common.domain.repository.MinSupportedVersionRepository
import javax.inject.Inject

class MinSupportedVersionRepositoryImpl @Inject constructor(
    private val minSupportedVersionService: MinSupportedVersionService
) : MinSupportedVersionRepository {

    override suspend fun fetchMinSupportedVersion(): MinSupportedVersion {
        val response = minSupportedVersionService.getMinSupportedVersion()
        return response.toData().toDomain()
    }
}
