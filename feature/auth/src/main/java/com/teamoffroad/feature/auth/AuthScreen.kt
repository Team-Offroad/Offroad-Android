package com.teamoffroad.feature.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun AuthScreen(
    padding: PaddingValues,
) {
    Box() {
        Text(
            "auth screen",
            fontSize = 40.sp,
            textAlign = TextAlign.Center
        )
    }
}