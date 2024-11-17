package com.teamoffroad.core.common.data.repository

import com.teamoffroad.core.common.data.datasource.AutoSignInPreferencesDataSource
import com.teamoffroad.core.common.domain.repository.AutoSignInRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AutoSignInRepositoryImpl @Inject constructor(
    private val autoSignInPreferencesDataSource: AutoSignInPreferencesDataSource,
) : AutoSignInRepository {

    override val isAutoSignInEnabled: Flow<Boolean> = autoSignInPreferencesDataSource.autoLogin

    override suspend fun updateAutoSignInEnabled(enabled: Boolean) {
        autoSignInPreferencesDataSource.setAutoLogin(enabled)
    }
}