package com.teamoffroad.core.common.domain.repository

import kotlinx.coroutines.flow.Flow

interface AutoSignInRepository {
    val isAutoSignInEnabled: Flow<Boolean>
    suspend fun updateAutoSignInEnabled(enabled: Boolean)
}