package com.teamoffroad.feature.auth.domain.usecase

import com.teamoffroad.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class GetAutoSignInUseCase(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        return authRepository.isAutoSignInEnabled
    }
}
