package com.teamoffroad.core.common.domain.repository

import kotlinx.coroutines.flow.Flow

interface DeviceTokenRepository {
    val deviceToken: Flow<String>
    suspend fun updateDeviceTokenEnabled(deviceToken: String)
}