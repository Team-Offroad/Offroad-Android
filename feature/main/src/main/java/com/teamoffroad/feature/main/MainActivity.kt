package com.teamoffroad.feature.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.main.component.MainTransparentActionBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : ComponentActivity() {
    private val notificationTypeState = mutableStateOf<String?>(null)
    private val notificationIdState = mutableStateOf<String?>(null)
    private lateinit var characterBroadcastReceiver: CharacterChatBroadcastReceiver
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationTypeState.value = intent.getStringExtra(KEY_TYPE)
        notificationIdState.value = intent.getStringExtra(KEY_ID)
        characterBroadcastReceiver = CharacterChatBroadcastReceiver(
            navigateToAnnouncement = viewModel::navigateToAnnouncement,
        )
        CharacterChatBroadcastReceiver.register(this, characterBroadcastReceiver)

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()

            MainTransparentActionBar(window)
            OffroadTheme {
                MainScreen(
                    navigator = navigator,
                    modifier = Modifier,
                    notificationType = notificationTypeState.value,
                    notificationId = notificationIdState.value,
                    viewModel = viewModel,
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CharacterChatBroadcastReceiver.unregister(this, characterBroadcastReceiver)
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}