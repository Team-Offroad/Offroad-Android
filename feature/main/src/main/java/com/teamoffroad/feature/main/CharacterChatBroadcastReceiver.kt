package com.teamoffroad.feature.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_BODY
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TITLE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.common.domain.model.NotificationEvent
import org.greenrobot.eventbus.EventBus

class CharacterChatBroadcastReceiver(
    private val navigateToAnnouncement: (id: String) -> Unit,
    private val navigateToHome: (characterName: String, characterChatting: String) -> Unit,
) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        when (intent.action) {
            ACTION_ANNOUNCEMENT_FOREGROUND -> {
                val announcementID = intent.getStringExtra(KEY_ID)
                if (announcementID != null) {
                    Log.d("asdasd", announcementID)
                    navigateToAnnouncement(announcementID)
                }
            }

            ACTION_CHARACTER_CHAT_FOREGROUND -> {
                val characterName = intent.getStringExtra(KEY_TITLE)
                val characterChatting = intent.getStringExtra(KEY_BODY)
                if (characterChatting != null && characterName != null) {
                    Log.d("asdasd", characterName)
                    navigateToHome(characterName, characterChatting)
                }
            }
        }

        //아까 브로드캐스트한 값들을 이벤트버스에 담아서 게시합니다. 홈화면에서 실시간으로 받을 수 있게하기 위해서!
        //다음 메인액티비티로 가면 됩니다.
        val notificationTitle = intent.getStringExtra(KEY_TITLE)
        val notificationBody = intent.getStringExtra(KEY_BODY)
        val notificationType = intent.getStringExtra(KEY_TYPE)

        EventBus.getDefault()
            .post(NotificationEvent(notificationTitle, notificationBody, notificationType))
    }

    companion object {
        const val ACTION_CHARACTER_CHAT_FOREGROUND =
            "com.teamoffroad.offroad.app.CHARACTER_CHAT_FOREGROUND"
        const val ACTION_ANNOUNCEMENT_FOREGROUND =
            "com.teamoffroad.offroad.app.ANNOUNCEMENT_FOREGROUND"

        fun register(context: Context, receiver: CharacterChatBroadcastReceiver) {
            val intentFilter = IntentFilter().apply {
                addAction(ACTION_CHARACTER_CHAT_FOREGROUND)
                addAction(ACTION_ANNOUNCEMENT_FOREGROUND)
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