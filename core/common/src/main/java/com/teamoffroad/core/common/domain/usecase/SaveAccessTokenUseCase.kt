package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.TokenRepository

class SaveAccessTokenUseCase(
    private val tokenRepository: TokenRepository,
) {
    suspend operator fun invoke(accessToken: String) {
        tokenRepository.saveAccessToken(accessToken)
    }
}
