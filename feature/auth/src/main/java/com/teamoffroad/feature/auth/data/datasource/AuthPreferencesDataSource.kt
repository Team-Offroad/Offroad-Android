package com.teamoffroad.feature.auth.data.datasource

import kotlinx.coroutines.flow.Flow

interface AuthPreferencesDataSource {
    val autoLogin: Flow<Boolean>

    suspend fun setAutoLogin(autoLogin: Boolean)
}
