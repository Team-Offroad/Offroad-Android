package com.teamoffroad.characterchat.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Composable
fun rememberKeyboardHeight(): Int {
    val view = LocalView.current
    var keyboardHeightPx by remember { mutableIntStateOf(0) }

    val density = LocalDensity.current

    DisposableEffect(view) {
        val listener = androidx.core.view.OnApplyWindowInsetsListener { _, insetsCompat ->
            val imeHeight = insetsCompat.getInsets(WindowInsetsCompat.Type.ime()).bottom
            keyboardHeightPx = imeHeight
            insetsCompat
        }

        ViewCompat.setOnApplyWindowInsetsListener(view, listener)

        onDispose {
            ViewCompat.setOnApplyWindowInsetsListener(view, null)
        }
    }

    val result = with(density) { keyboardHeightPx.toDp().value.toInt() }
    return if (result > 0) result else DEFAULT_IME_PADDING
}

const val DEFAULT_IME_PADDING = 300
