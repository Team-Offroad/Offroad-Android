package com.teamoffroad.feature.main.component

import android.graphics.Color
import android.view.Window
import android.view.WindowManager
import androidx.compose.runtime.Composable

@Composable
fun MainTransparentActionBar(window: Window) {
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
    )

    window.navigationBarColor = Color.TRANSPARENT
}
