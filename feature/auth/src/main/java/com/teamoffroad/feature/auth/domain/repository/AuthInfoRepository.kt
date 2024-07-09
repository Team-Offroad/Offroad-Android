package com.teamoffroad.feature.auth.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthInfoRepository {
    fun getAccessToken(): Flow<String>
    fun getRefreshToken(): Flow<String>
    fun getAutoLogin(): Flow<Boolean>

    suspend fun saveAccessToken(accessToken: String)
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun saveAutoLogin(autoLogin: Boolean)
}