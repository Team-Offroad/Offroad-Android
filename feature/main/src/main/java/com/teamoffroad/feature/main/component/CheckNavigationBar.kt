package com.teamoffroad.feature.main.component

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun CheckNavigationBar(view: View): Boolean = remember {
    val systemBottomNavigationSetting =
        view.context.resources.getIdentifier("config_navBarInteractionMode", "integer", "android")
    systemBottomNavigationSetting > UNAVAILABLE_RESOURCE_ID && view.context.resources.getInteger(
        systemBottomNavigationSetting
    ) == GESTURE_NAVIGATION
}

private const val GESTURE_NAVIGATION = 2
private const val UNAVAILABLE_RESOURCE_ID = 0