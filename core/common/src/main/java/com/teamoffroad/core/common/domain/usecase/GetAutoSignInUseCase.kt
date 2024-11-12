package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.AutoSignInRepository
import kotlinx.coroutines.flow.Flow

class GetAutoSignInUseCase(
    private val autoSignInRepository: AutoSignInRepository,
) {
    operator fun invoke(): Flow<Boolean> {
        return autoSignInRepository.isAutoSignInEnabled
    }
}
