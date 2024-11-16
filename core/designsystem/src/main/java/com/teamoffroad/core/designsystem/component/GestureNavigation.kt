package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun Modifier.navigationPadding(
    backgroundColor: Color = Color.Transparent,
): Modifier {
    return this
        .background(color = backgroundColor)
        .then(
            padding(bottom = getNavigationBarHeight())
        )
}

@Composable
private fun getNavigationBarHeight(): Dp {
    val view = LocalView.current
    val insets = ViewCompat.getRootWindowInsets(view)?.getInsets(WindowInsetsCompat.Type.navigationBars())
    val navigationBarHeightPx = insets?.bottom ?: 0
    val density = LocalDensity.current
    return with(density) { navigationBarHeightPx.toDp() }
}
