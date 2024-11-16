package com.teamoffroad.offroad.app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import coil.Coil
import coil.request.ImageRequest
import com.google.firebase.messaging.Constants.MessageNotificationKeys
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
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

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage.data.isNotEmpty()) {
            if (ActivityLifecycleHandler.isAppInForeground) {
                //TODO. 키 밸류값 나오면 바꾸기
                if (remoteMessage.data[KEY_TYPE] != TYPE_CHARACTER_CHAT)
                    sendForeGroundNotification(remoteMessage, true)
            } else {
                sendBackGroundNotification(remoteMessage, false)
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

    private fun createNotificationIntent(remoteMessage: RemoteMessage): Intent {
        return Intent(this, MainActivity::class.java).apply {
            if (ActivityLifecycleHandler.isAppInForeground) {
                Log.d("asdasd", "forground")
            } else {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            putExtra(KEY_TYPE, remoteMessage.data[KEY_TYPE])
            if (remoteMessage.data[KEY_TYPE] != TYPE_CHARACTER_CHAT) {
                putExtra(KEY_ID, remoteMessage.data[KEY_ID])
            }
        }
    }

    private fun createForeGroundNotificationBuilder(
        remoteMessage: RemoteMessage,
        onLargeIconReady: (NotificationCompat.Builder) -> Unit
    ): NotificationCompat.Builder {

        val notificationBuilder = NotificationCompat.Builder(this, "channelId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(remoteMessage.data[KEY_TITLE])
            .setContentText(remoteMessage.data[KEY_BODY])
            .setAutoCancel(true)
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

    private fun createBackGroundNotificationBuilder(
        remoteMessage: RemoteMessage,
        pendingIntent: PendingIntent,
        onLargeIconReady: (NotificationCompat.Builder) -> Unit
    ): NotificationCompat.Builder {

        val notificationBuilder = NotificationCompat.Builder(this, "channelId")
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

    private fun sendForeGroundNotification(remoteMessage: RemoteMessage, isForeGround: Boolean) {
        val uniqueIdentifier = generateUniqueIdentifier()
        createForeGroundNotificationBuilder(remoteMessage) { notificationBuilder ->
            showNotification(notificationBuilder, uniqueIdentifier)

            if(isForeGround){

            }
        }
    }

    private fun sendBackGroundNotification(remoteMessage: RemoteMessage, isForeGround: Boolean) {
        val uniqueIdentifier = generateUniqueIdentifier()
        val intent = createNotificationIntent(remoteMessage)
        val pendingIntent = createPendingIntent(intent, uniqueIdentifier)
        createBackGroundNotificationBuilder(remoteMessage, pendingIntent) { notificationBuilder ->
            showNotification(notificationBuilder, uniqueIdentifier)
        }
    }

    private fun showNotification(
        notificationBuilder: NotificationCompat.Builder,
        uniqueIdentifier: Int
    ) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel("channelId", "Notice", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(uniqueIdentifier, notificationBuilder.build())
    }

    companion object {
        //TODO. 나중에 정리 해야됨
        private const val KEY_TITLE = "title"
        private const val KEY_BODY = "body"
        private const val KEY_TYPE = "type"
        private const val KEY_IMAGE = "image"
        private const val KEY_ID = "additionalProp1"
        private const val TYPE_CHARACTER_CHAT = "CHARACTER_CHAT"
        private const val TYPE_ANNOUNCEMENT = "ANNOUNCEMENT_REDIRECT"

    }
}