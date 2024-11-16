package com.teamoffroad.feature.main

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.main.component.MainTransparentActionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : ComponentActivity() {
    private val notificationTypeState = mutableStateOf<String?>(null)
    private val notificationIdState = mutableStateOf<String?>(null)
    private val myBroadcastReceiver = CharacterChatBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationTypeState.value = intent.getStringExtra(KEY_TYPE)
        notificationIdState.value = intent.getStringExtra(KEY_ID)

        //액티비티 생명주기에 따라 브로드캐스터리시버 만들어주기
        //밑에 onDestroy에서 브로드캐스트리시버 해제도 해줍니다.
        //이제 홈 뷰모델로 가면됩니다.
        val filter = IntentFilter("com.teamoffroad.offroad.app.ACTION_RECEIVE_NOTIFICATION")
        registerReceiver(myBroadcastReceiver, filter, RECEIVER_NOT_EXPORTED)

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            val showSplash = remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(1550)
                showSplash.value = false
            }
            MainTransparentActionBar(window)
            OffroadTheme {
                when (showSplash.value) {
                    true -> SplashScreen()
                    false -> MainScreen(
                        navigator = navigator,
                        modifier = Modifier,
                        notificationType = notificationTypeState.value,
                        notificationId = notificationIdState.value,
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myBroadcastReceiver)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        updateNotificationData(intent)

        Log.d("asdasd", "forground $notificationTypeState")
        Log.d("asdasd", "forground $notificationIdState")
    }

    private fun updateNotificationData(intent: Intent) {
        notificationTypeState.value = intent.getStringExtra(KEY_TYPE)
        notificationIdState.value = intent.getStringExtra(KEY_ID)
        lifecycleScope.launch {
            delay(1500L)
            notificationTypeState.value = null
            notificationIdState.value = null
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}

@Preview(showBackground = true)
@Composable
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun GreetingPreview() {
    OffroadTheme {
        MainScreen(notificationType = "", notificationId = "")
    }
}

