package com.teamoffroad.feature.main.component

import android.graphics.Color
import android.view.Window
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.teamoffroad.core.designsystem.theme.Sub4_80

@Composable
fun MainTransparentActionBar(window: Window) {
    WindowCompat.setDecorFitsSystemWindows(window, false)

    window.statusBarColor = Color.TRANSPARENT
    window.navigationBarColor = Sub4_80.toArgb()

    val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
    windowInsetsController.isAppearanceLightStatusBars = true
    windowInsetsController.isAppearanceLightNavigationBars = false
}
