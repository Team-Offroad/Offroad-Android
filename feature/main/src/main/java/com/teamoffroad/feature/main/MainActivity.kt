package com.teamoffroad.feature.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.teamoffroad.characterchat.presentation.MainCharacterChatViewModel
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_ID
import com.teamoffroad.core.common.domain.model.FcmNotificationKey.KEY_TYPE
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.main.component.MainTransparentActionBar
import com.teamoffroad.feature.main.navigation.MainNavigator
import com.teamoffroad.feature.main.navigation.rememberMainNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : ComponentActivity() {
    private val notificationTypeState = mutableStateOf<String?>(null)
    private val notificationIdState = mutableStateOf<String?>(null)
    private lateinit var characterBroadcastReceiver: FcmBroadcastReceiver
    private val viewModel by viewModels<MainViewModel>()
    private val mainCharacterViewModel by viewModels<MainCharacterChatViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentVersionInfo = getAppVersion()
        viewModel.getMinSupportedVersion(currentVersionInfo)

        notificationTypeState.value = intent.getStringExtra(KEY_TYPE)
        notificationIdState.value = intent.getStringExtra(KEY_ID)
        characterBroadcastReceiver = FcmBroadcastReceiver(
            navigateToAnnouncement = viewModel::navigateToAnnouncement,
        )
        FcmBroadcastReceiver.register(this, characterBroadcastReceiver)

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            val appUpdateDialogShown = remember { mutableStateOf(false) }
            val appVersionState by viewModel.appVersionState.collectAsState(initial = true)

            LaunchedEffect(appVersionState) {
                if (!appVersionState) {
                    appUpdateDialogShown.value = true
                }
            }

            MainTransparentActionBar(window)
            OffroadTheme {
                MainScreen(
                    navigator = navigator,
                    modifier = Modifier,
                    notificationType = notificationTypeState.value,
                    notificationId = notificationIdState.value,
                    mainViewModel = viewModel,
                    mainCharacterViewModel = mainCharacterViewModel,
                )

                if (appUpdateDialogShown.value) {
                    AppUpdateDialog(
                        onDismissRequest = { appUpdateDialogShown.value = false },
                        context = LocalContext.current
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FcmBroadcastReceiver.unregister(this, characterBroadcastReceiver)
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun getAppVersion(): String {
        return try {
            val packageInfo = packageManager.getPackageInfo(packageName, 0)
            packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "Unknown"
        }
    }
}