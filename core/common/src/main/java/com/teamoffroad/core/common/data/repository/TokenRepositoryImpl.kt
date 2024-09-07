package com.teamoffroad.core.common.data.repository

import androidx.datastore.core.IOException
import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.data.mapper.toDomain
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.core.common.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenService: TokenService,
    private val tokenPreferencesDataSource: TokenPreferencesDataSource,
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
        return tokenPreferencesDataSource.accessToken
    }

    override fun getRefreshToken(): Flow<String?> {
        return tokenPreferencesDataSource.refreshToken
    }

    override suspend fun saveAccessToken(token: String) {
        tokenPreferencesDataSource.setAccessToken(token)
    }

    override suspend fun saveRefreshToken(token: String) {
        tokenPreferencesDataSource.setRefreshToken(token)
    }
}
