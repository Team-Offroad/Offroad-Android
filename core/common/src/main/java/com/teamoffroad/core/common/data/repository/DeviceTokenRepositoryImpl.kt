package com.teamoffroad.core.common.data.repository

import com.teamoffroad.core.common.data.datasource.DeviceTokenPreferencesDataSource
import com.teamoffroad.core.common.domain.repository.DeviceTokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeviceTokenRepositoryImpl @Inject constructor(
    private val deviceTokenPreferencesDataSource: DeviceTokenPreferencesDataSource,
) : DeviceTokenRepository {

    override val deviceToken: Flow<String> = deviceTokenPreferencesDataSource.deviceToken

    override suspend fun updateDeviceTokenEnabled(deviceToken: String) {
        deviceTokenPreferencesDataSource.setDeviceToken(deviceToken)
    }
}