package com.teamoffroad.feature.auth.data.datasource

import kotlinx.coroutines.flow.Flow

interface AuthPreferencesDataSource {
    val autoLogin: Flow<String>

    suspend fun setAutoLogin(socialPlatform: String)
}
