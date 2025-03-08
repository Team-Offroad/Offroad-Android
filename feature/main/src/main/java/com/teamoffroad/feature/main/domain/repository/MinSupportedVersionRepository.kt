package com.teamoffroad.feature.main.domain.repository

import com.teamoffroad.feature.main.domain.model.MinSupportedVersion

interface MinSupportedVersionRepository {
    suspend fun fetchMinSupportedVersion(): MinSupportedVersion
}
