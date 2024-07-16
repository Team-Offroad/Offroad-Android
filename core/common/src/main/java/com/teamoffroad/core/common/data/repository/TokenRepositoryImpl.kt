package com.teamoffroad.core.common.data.repository

import androidx.datastore.core.IOException
import com.teamoffroad.core.common.data.local.TokenManager
import com.teamoffroad.core.common.data.mapper.toDomain
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.core.common.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenService: TokenService,
    private val tokenManager: TokenManager,
) : TokenRepository {

    override suspend fun refreshAccessToken(refreshToken: String): Token? {
        return try {
            val response = tokenService.refreshAccessToken("Bearer $refreshToken")
            response.data?.toDomain()
        } catch (e: IOException) {
            null
        }
    }

    override fun getAccessToken(): Flow<String?> {
        return tokenManager.getAccessToken()
    }

    override fun getRefreshToken(): Flow<String?> {
        return tokenManager.getRefreshToken()
    }

    override suspend fun saveAccessToken(token: String) {
        tokenManager.saveAccessToken(token)
    }

    override suspend fun saveRefreshToken(token: String) {
        tokenManager.saveRefreshToken(token)
    }
}
