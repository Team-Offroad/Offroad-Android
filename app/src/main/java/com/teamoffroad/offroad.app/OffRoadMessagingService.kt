package com.teamoffroad.offroad.app

import com.google.firebase.messaging.FirebaseMessagingService
import com.teamoffroad.core.common.domain.repository.DeviceTokenRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OffRoadMessagingService : FirebaseMessagingService() {
    @Inject
    lateinit var dataStore: DeviceTokenRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        CoroutineScope(Dispatchers.IO).launch { dataStore.updateDeviceTokenEnabled(token) }
    }
}