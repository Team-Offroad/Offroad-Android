package com.teamoffroad.core.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CircularLoadingAnimationLine(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
) {
    if (isLoading) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center,
        ) {
            CircularLoadingAnimation(isLoading = true)
        }
    }
}