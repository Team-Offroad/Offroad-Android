package com.teamoffroad.feature.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun HomeScreen(
    padding: PaddingValues,
) {
    Text(
        "home screen",
        fontSize = 40.sp,
        textAlign = TextAlign.Center
    )
}