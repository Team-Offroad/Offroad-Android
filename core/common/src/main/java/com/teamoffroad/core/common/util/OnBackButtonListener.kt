package com.teamoffroad.core.common.util

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.getString
import com.teamoffroad.offroad.core.common.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnBackButtonListener(
    navigateToBack: () -> Unit,
    isEnabled: Boolean = false,
) {
    var backPressedOnce by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        when {
            backPressedOnce && isEnabled -> (context as? ComponentActivity)?.finish()
            !backPressedOnce && isEnabled -> {
                backPressedOnce = true
                Toast.makeText(context, getString(context, R.string.main_back_hint), Toast.LENGTH_SHORT).show()
                coroutineScope.launch {
                    delay(2000L)
                    backPressedOnce = false
                }
            }

            else -> navigateToBack()
        }
    }
}