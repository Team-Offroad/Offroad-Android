package com.teamoffroad.feature.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.main.component.MainTransparentActionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationType = intent.getStringExtra("type")
        val notificationId = intent.getStringExtra("additionalProp1")
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
                        notificationType = if (!notificationType.isNullOrBlank()) notificationType else null,
                        notificationId = if (!notificationId.isNullOrBlank()) notificationId else null,
                    )
                }
            }
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

