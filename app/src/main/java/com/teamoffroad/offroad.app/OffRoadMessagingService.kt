package com.teamoffroad.offroad.app

import com.google.firebase.messaging.FirebaseMessagingService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OffRoadMessagingService : FirebaseMessagingService() {
   
    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }
}