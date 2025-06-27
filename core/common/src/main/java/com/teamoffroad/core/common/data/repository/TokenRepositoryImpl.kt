package com.teamoffroad.core.common.data.repository

import com.teamoffroad.core.common.data.mapper.toDomain
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.model.Token
import com.teamoffroad.core.common.domain.preferences.TokenPreferences
import com.teamoffroad.core.common.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl
    @Inject
    constructor(
        private val tokenService: TokenService,
        private val tokenPreferences: TokenPreferences,
    ) : TokenRepository {
        override suspend fun refreshAccessToken(refreshToken: String): Token? =
            tokenService.refreshAccessToken(refreshToken).data?.toDomain()

        override fun getAccessToken(): Flow<String?> = tokenPreferences.accessToken

        override fun getRefreshToken(): Flow<String?> = tokenPreferences.refreshToken

        override suspend fun saveAccessToken(token: String) {
            tokenPreferences.setAccessToken(token)
        }

        override suspend fun saveRefreshToken(token: String) {
            tokenPreferences.setRefreshToken(token)
        }

        override suspend fun clearTokens() {
            tokenPreferences.clearTokens()
        }

        override suspend fun updateDeviceTokenEnabled(deviceToken: String) {
            tokenPreferences.setDeviceToken(deviceToken)
        }

        override fun getDeviceToken(): Flow<String> = tokenPreferences.deviceToken
    }
