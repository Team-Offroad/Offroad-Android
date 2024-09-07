package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.TokenRepository

class ClearTokensUseCase(
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke() {
        tokenRepository.clearTokens()
    }
}
