package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.AutoSignInRepository

class SetAutoSignInUseCase(
    private val autoSignInRepository: AutoSignInRepository,
) {
    suspend operator fun invoke(enabled: Boolean) {
        autoSignInRepository.updateAutoSignInEnabled(enabled)
    }
}