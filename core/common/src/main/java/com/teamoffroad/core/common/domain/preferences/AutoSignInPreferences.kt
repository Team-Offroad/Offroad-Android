package com.teamoffroad.core.common.domain.preferences

import kotlinx.coroutines.flow.Flow

interface AutoSignInPreferences {
    val autoLogin: Flow<Boolean>

    suspend fun setAutoLogin(autoLogin: Boolean)
}
