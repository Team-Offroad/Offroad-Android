package com.teamoffroad.core.datastore.datasource

import kotlinx.coroutines.flow.Flow

interface AuthPreferencesDataSource {
    val accessToken: Flow<String>
    val refreshToken: Flow<String>
    val autoLogin: Flow<Boolean>

    suspend fun setAccessToken(accessToken: String)
    suspend fun setRefreshToken(refreshToken: String)
    suspend fun setAutoLogin(autoLogin: Boolean)
}