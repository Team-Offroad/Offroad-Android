package com.teamoffroad.core.common.data.repository

import com.teamoffroad.core.common.domain.preferences.AutoSignInPreferences
import com.teamoffroad.core.common.domain.repository.AutoSignInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AutoSignInRepositoryImpl
    @Inject
    constructor(
        private val autoSignInPreferences: AutoSignInPreferences,
    ) : AutoSignInRepository {
        override val isAutoSignInEnabled: Flow<Boolean> = autoSignInPreferences.autoLogin

        override suspend fun updateAutoSignInEnabled(enabled: Boolean) {
            autoSignInPreferences.setAutoLogin(enabled)
        }
    }
