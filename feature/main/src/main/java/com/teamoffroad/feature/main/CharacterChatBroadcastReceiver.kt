package com.teamoffroad.feature.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_BODY
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TITLE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.common.domain.model.NotificationEvent
import org.greenrobot.eventbus.EventBus

class CharacterChatBroadcastReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onReceive(context: Context, intent: Intent) {
        //아까 브로드캐스트한 값들을 이벤트버스에 담아서 게시합니다. 홈화면에서 실시간으로 받을 수 있게하기 위해서!
        //다음 메인액티비티로 가면 됩니다.
        val notificationTitle = intent.getStringExtra(KEY_TITLE)
        val notificationBody = intent.getStringExtra(KEY_BODY)
        val notificationType = intent.getStringExtra(KEY_TYPE)

        EventBus.getDefault()
            .post(NotificationEvent(notificationTitle, notificationBody, notificationType))
    }
}