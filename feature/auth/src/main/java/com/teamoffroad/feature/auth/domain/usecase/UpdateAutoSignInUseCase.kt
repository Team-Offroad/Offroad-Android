package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class UpdateAutoSignInUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(socialPlatform: String) {
        authRepository.updateAutoSignInEnabled(socialPlatform)
    }
}