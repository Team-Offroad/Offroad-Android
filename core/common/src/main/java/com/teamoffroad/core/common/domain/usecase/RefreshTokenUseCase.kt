package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.core.common.domain.repository.TokenRepository

class RefreshTokenUseCase(
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(refreshToken: String): Token? {
        return tokenRepository.refreshAccessToken(refreshToken)
    }
}
