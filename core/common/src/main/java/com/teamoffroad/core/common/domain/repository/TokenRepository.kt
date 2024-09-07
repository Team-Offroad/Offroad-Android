package com.teamoffroad.core.common.domain.repository

import com.teamoffroad.core.common.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface TokenRepository {
    suspend fun refreshAccessToken(refreshToken: String): Token?

    suspend fun saveAccessToken(token: String)

    suspend fun saveRefreshToken(token: String)

    fun getAccessToken(): Flow<String?>

    fun getRefreshToken(): Flow<String?>

    suspend fun clearTokens()
}
