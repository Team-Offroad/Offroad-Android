package com.teamoffroad.feature.main

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.feature.main.component.MainTransparentActionBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val a = intent.getStringExtra("type")
        if (a != "CHARACTER_CHAT") {
            val b = intent.getStringExtra("additionalProp1")
            Log.d("fcmCHARACTER_CHAT", a.toString())
            Log.d("fcmadditionalProp1", b.toString())
        }

        setContent {
            val navigator: MainNavigator = rememberMainNavigator()
            MainTransparentActionBar(window)
            OffroadTheme {
                MainScreen(
                    navigator = navigator,
                    modifier = Modifier
                )
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
        MainScreen()
    }
}

