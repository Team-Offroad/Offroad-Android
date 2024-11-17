package com.teamoffroad.core.common.data.datasource

import kotlinx.coroutines.flow.Flow

interface DeviceTokenPreferencesDataSource {
    val deviceToken: Flow<String>
    suspend fun setDeviceToken(deviceToken: String)
}