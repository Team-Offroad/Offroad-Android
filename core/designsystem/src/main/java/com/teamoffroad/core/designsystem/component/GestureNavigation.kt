package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.BottomBar

@Composable
fun GestureNavigation(
    backgroundColor: Color = BottomBar,
    modifier: Modifier = Modifier.background(backgroundColor)
): Modifier {
    return if (!CheckNavigationBar()) modifier.navigationBarsPadding()
    else modifier.padding(bottom = 14.dp)
}

@Composable
private fun CheckNavigationBar(): Boolean {
    val view = LocalView.current
    return remember {
        val systemBottomNavigationSetting = view.context.resources.getIdentifier(
            "config_navBarInteractionMode",
            "integer",
            "android"
        )
        systemBottomNavigationSetting > UNAVAILABLE_RESOURCE_ID && view.context.resources.getInteger(
            systemBottomNavigationSetting
        ) == GESTURE_NAVIGATION
    }
}

private const val GESTURE_NAVIGATION = 2
private const val UNAVAILABLE_RESOURCE_ID = 0