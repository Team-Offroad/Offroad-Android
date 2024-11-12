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

@Composable
fun Modifier.navigationPadding(
    backgroundColor: Color = Color.Transparent,
): Modifier {
    val isGestureNavigation = checkNavigationBar()
    return this
        .background(color = backgroundColor)
        .then(
            when (isGestureNavigation) {
                true -> navigationBarsPadding()
                false -> padding(bottom = (11.8).dp)
            }
        )
}

@Composable
fun checkNavigationBar(): Boolean {
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