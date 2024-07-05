package com.teamoffroad.feature.explore

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
internal fun ExploreRoute(
    padding: PaddingValues,
) {
    ExploreScreen(
        padding = padding,
    )
}

@Composable
internal fun ExploreScreen(
    padding: PaddingValues,
) {
    Text(
        "explore screen",
        fontSize = 40.sp,
        textAlign = TextAlign.Center
    )

}