package com.teamoffroad.feature.diary.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.DiaryProgressBar
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun DiaryHintProgressIndicator(
    modifier: Modifier = Modifier,
    animateActive: Boolean,
) {
    val animatedColor by animateColorAsState(
        targetValue = if (animateActive) White else DiaryProgressBar,
        animationSpec = tween(durationMillis = 600),
        label = "progressIndicator"
    )

    Box(modifier = modifier) {
        HorizontalDivider(
            modifier = Modifier
                .width(18.dp),
            thickness = 4.dp,
            color = animatedColor,
        )
    }
}