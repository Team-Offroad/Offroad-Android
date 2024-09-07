package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class ClearDataStoreUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke() {
        authRepository.clearDataStore()
    }
}