package com.teamoffroad.feature.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.ACTION_ANNOUNCEMENT_FOREGROUND
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.ACTION_CHARACTER_CHAT_FOREGROUND
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_BODY
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TITLE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.common.domain.model.NotificationEvent
import org.greenrobot.eventbus.EventBus

class CharacterChatBroadcastReceiver(
    private val navigateToAnnouncement: (id: String) -> Unit,
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            ACTION_ANNOUNCEMENT_FOREGROUND -> {
                val announcementID = intent.getStringExtra(KEY_ID)
                if (announcementID != null) {
                    navigateToAnnouncement(announcementID)
                }
            }

            ACTION_CHARACTER_CHAT_FOREGROUND -> {
                updateCharacterChatReceived(intent)
            }
        }
    }

    private fun updateCharacterChatReceived(intent: Intent) {
        val notificationTitle = intent.getStringExtra(KEY_TITLE)
        val notificationBody = intent.getStringExtra(KEY_BODY)
        val notificationType = intent.getStringExtra(KEY_TYPE)

        EventBus.getDefault()
            .post(NotificationEvent(notificationTitle, notificationBody, notificationType))
    }

    companion object {
        fun register(context: Context, receiver: CharacterChatBroadcastReceiver) {
            val intentFilter = IntentFilter().apply {
                addAction(ACTION_ANNOUNCEMENT_FOREGROUND)
                addAction(ACTION_CHARACTER_CHAT_FOREGROUND)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED)
            } else {
                context.registerReceiver(receiver, intentFilter)
            }
        }

        fun unregister(context: Context, receiver: CharacterChatBroadcastReceiver) {
            context.unregisterReceiver(receiver)
        }
    }
}