package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow

class GetAccessTokenUseCase(
    private val tokenRepository: TokenRepository,
) {
    operator fun invoke(): Flow<String?> {
        return tokenRepository.getAccessToken()
    }
}
