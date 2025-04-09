package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.model.MinSupportedVersion
import com.teamoffroad.core.common.domain.repository.MinSupportedVersionRepository

class MinSupportedVersionUseCase(
    private val minSupportedVersionRepository: MinSupportedVersionRepository,
) {
    suspend operator fun invoke(): MinSupportedVersion {
        return minSupportedVersionRepository.fetchMinSupportedVersion()
    }
}
