package com.teamoffroad.feature.explore.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun ExploreScreen(
    padding: PaddingValues,
    viewModel: ExploreViewModel = hiltViewModel(),
) {

    Text(
        "explore screen",
        fontSize = 40.sp,
        textAlign = TextAlign.Center
    )
}