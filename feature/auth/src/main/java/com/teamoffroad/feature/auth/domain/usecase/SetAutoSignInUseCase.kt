package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.repository.AuthRepository

class SetAutoSignInUseCase(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(enabled: Boolean) {
        authRepository.setAutoSignInEnabled(enabled)
    }
}