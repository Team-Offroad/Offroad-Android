package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.AutoSignInRepository

class UpdateAutoSignInUseCase(
    private val autoSignInRepository: AutoSignInRepository,
) {
    suspend operator fun invoke(enabled: Boolean) {
        autoSignInRepository.updateAutoSignInEnabled(enabled)
    }
}