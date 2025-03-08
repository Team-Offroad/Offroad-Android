package com.teamoffroad.core.common.domain.repository

import com.teamoffroad.core.common.domain.model.MinSupportedVersion


interface MinSupportedVersionRepository {
    suspend fun fetchMinSupportedVersion(): MinSupportedVersion
}
