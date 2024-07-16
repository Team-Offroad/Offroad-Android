package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.TokenRepository

class SaveRefreshTokenUseCase(
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(refreshToken: String) {
        tokenRepository.saveRefreshToken(refreshToken)
    }
}
