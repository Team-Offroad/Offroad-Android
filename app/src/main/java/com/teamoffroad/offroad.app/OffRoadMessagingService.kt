package com.teamoffroad.offroad.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.core.app.NotificationCompat
import coil.Coil
import coil.request.ImageRequest
import com.google.firebase.messaging.Constants.MessageNotificationKeys
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.CHANNEL_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_BODY
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_IMAGE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TITLE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.NOTICE
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.TYPE_CHARACTER_CHAT
import com.teamoffroad.core.common.domain.repository.DeviceTokenRepository
import com.teamoffroad.core.common.util.ActivityLifecycleHandler
import com.teamoffroad.feature.main.MainActivity
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

    //FCM 메세지를 받을 때 호출됨
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            if (ActivityLifecycleHandler.isAppInForeground) {
                if (remoteMessage.data[KEY_TYPE] != TYPE_CHARACTER_CHAT)
                    sendNotification(remoteMessage, true)
                else {
                    // 앱이 포그라운드에 있고, 알림타임이 캐릭터채팅인 경우
                    // 정현이 봐야할곳은 여기!! 요쪽 따라가십쇼
                    sendCharacterNotificationInForeground(remoteMessage, true)
                }
            } else {
                sendNotification(remoteMessage, false)
            }
        }
    }

    override fun handleIntent(intent: Intent?) {
        val new = intent?.apply {
            val temp = extras?.apply {
                remove(MessageNotificationKeys.ENABLE_NOTIFICATION)
                remove(keyWithOldPrefix())
            }
            replaceExtras(temp)
        }
        super.handleIntent(new)
    }

    private fun keyWithOldPrefix(): String {
        if (!MessageNotificationKeys.ENABLE_NOTIFICATION.startsWith(MessageNotificationKeys.NOTIFICATION_PREFIX)) {
            return MessageNotificationKeys.ENABLE_NOTIFICATION
        }

        return MessageNotificationKeys.ENABLE_NOTIFICATION.replace(
            MessageNotificationKeys.NOTIFICATION_PREFIX,
            MessageNotificationKeys.NOTIFICATION_PREFIX_OLD
        )
    }

    private fun generateUniqueIdentifier(): Int {
        return (System.currentTimeMillis() / 7).toInt()
    }

    private fun createPendingIntent(intent: Intent, uniqueIdentifier: Int): PendingIntent {
        return PendingIntent.getActivity(
            this,
            uniqueIdentifier,
            intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun createNotificationIntent(
        remoteMessage: RemoteMessage,
        isForeGround: Boolean
    ): Intent {
        if (isForeGround) {
            return Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                putExtra(KEY_TYPE, remoteMessage.data[KEY_TYPE])
                if (remoteMessage.data[KEY_TYPE] != TYPE_CHARACTER_CHAT) {
                    putExtra(KEY_ID, remoteMessage.data[KEY_ID])
                }
            }
        } else {
            return Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(KEY_TYPE, remoteMessage.data[KEY_TYPE])
                if (remoteMessage.data[KEY_TYPE] != TYPE_CHARACTER_CHAT) {
                    putExtra(KEY_ID, remoteMessage.data[KEY_ID])
                }
            }
        }
    }

    private fun createNotificationBuilder(
        remoteMessage: RemoteMessage,
        pendingIntent: PendingIntent,
        onLargeIconReady: (NotificationCompat.Builder) -> Unit
    ): NotificationCompat.Builder {

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(remoteMessage.data[KEY_TITLE])
            .setContentText(remoteMessage.data[KEY_BODY])
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_MAX)

        val imageUrl = remoteMessage.data[KEY_IMAGE]
        imageUrl?.let {
            val request = ImageRequest.Builder(this)
                .data(it)
                .target { drawable ->
                    val bitmap = (drawable as BitmapDrawable).bitmap
                    notificationBuilder.setLargeIcon(bitmap)
                    onLargeIconReady(notificationBuilder)
                }
                .build()

            Coil.imageLoader(this).enqueue(request)
        } ?: run {
            onLargeIconReady(notificationBuilder)
        }
        return notificationBuilder
    }

    private fun sendNotification(remoteMessage: RemoteMessage, isForeGround: Boolean) {
        val uniqueIdentifier = generateUniqueIdentifier()
        val intent = createNotificationIntent(remoteMessage, isForeGround)
        val pendingIntent = createPendingIntent(intent, uniqueIdentifier)
        createNotificationBuilder(remoteMessage, pendingIntent) { notificationBuilder ->
            showNotification(notificationBuilder, uniqueIdentifier)
        }
    }

    //브로드캐스트리시버에 필요한 데이터(캐릭터이름, 대화내용, 알림타입) 저장하고 브로드캐스트 발신
    //feature main의 CharacterChatBroadcastReceiver로 가면 됩니다.
    private fun sendCharacterNotificationInForeground(
        remoteMessage: RemoteMessage,
        isForeGround: Boolean
    ) {
        val intent = Intent("com.teamoffroad.offroad.app.ACTION_RECEIVE_NOTIFICATION").apply {
            putExtra(KEY_TITLE, remoteMessage.data[KEY_TITLE])
            putExtra(KEY_BODY, remoteMessage.data[KEY_BODY])
            putExtra(KEY_TYPE, remoteMessage.data[KEY_TYPE])
        }
        sendBroadcast(intent)
    }

    private fun showNotification(
        notificationBuilder: NotificationCompat.Builder,
        uniqueIdentifier: Int
    ) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, NOTICE, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(uniqueIdentifier, notificationBuilder.build())
    }
}