package com.teamoffroad.core.common.domain.preferences

import kotlinx.coroutines.flow.Flow

interface TokenPreferences {
    val accessToken: Flow<String>
    val refreshToken: Flow<String>
    val deviceToken: Flow<String>

    suspend fun setAccessToken(accessToken: String)

    suspend fun setRefreshToken(refreshToken: String)

    suspend fun clearTokens()

    suspend fun setDeviceToken(deviceToken: String)
}
