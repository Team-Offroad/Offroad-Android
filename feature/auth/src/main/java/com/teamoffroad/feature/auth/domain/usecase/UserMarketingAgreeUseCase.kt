package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class UserMarketingAgreeUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(marketingAgree: Boolean): Result<Unit> {
        return authRepository.saveMarketingAgree(marketingAgree)
    }
}