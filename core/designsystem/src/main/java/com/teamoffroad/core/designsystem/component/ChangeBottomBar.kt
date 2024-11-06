package com.teamoffroad.core.designsystem.component

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
fun ChangeBottomBarColor(color: Color) {
    val activity = LocalContext.current as? Activity
    val window = activity?.window
    window?.navigationBarColor = color.toArgb()
}