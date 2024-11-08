package com.teamoffroad.core.common.data.datasource

import kotlinx.coroutines.flow.Flow

interface AutoSignInPreferencesDataSource {
    val autoLogin: Flow<Boolean>

    suspend fun setAutoLogin(autoLogin: Boolean)
}
