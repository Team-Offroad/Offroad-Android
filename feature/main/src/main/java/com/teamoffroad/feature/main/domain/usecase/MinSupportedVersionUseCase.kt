package com.teamoffroad.feature.main.domain.usecase

import com.teamoffroad.feature.home.domain.model.DummyUser
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.main.domain.model.MinSupportedVersion
import com.teamoffroad.feature.main.domain.repository.MinSupportedVersionRepository

class MinSupportedVersionUseCase(
    private val minSupportedVersionRepository: MinSupportedVersionRepository,
) {
    suspend operator fun invoke(): MinSupportedVersion {
        return minSupportedVersionRepository.fetchMinSupportedVersion()
    }
}
