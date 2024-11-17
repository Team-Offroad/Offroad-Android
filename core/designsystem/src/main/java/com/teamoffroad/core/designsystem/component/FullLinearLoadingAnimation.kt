package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.teamoffroad.core.designsystem.theme.Black55

@Composable
fun FullLinearLoadingAnimation(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Black55),
            contentAlignment = Alignment.Center,
        ) {
            LinearLoadingAnimation(isLoading = true)
        }
    }
}