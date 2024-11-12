package com.teamoffroad.core.common.domain.repository

import kotlinx.coroutines.flow.Flow

interface DeviceTokenRepository {
    val isDeviceTokenEnabled: Flow<String>
    suspend fun updateDeviceTokenEnabled(deviceToken: String)
}