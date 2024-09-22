package com.teamoffroad.feature.explore.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.MapGradiEnd
import com.teamoffroad.core.designsystem.theme.MapGradiStart

@Composable
fun ExploreMapForeground() {
    Box(Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = true,
            enter = EnterTransition.None,
            exit = ExitTransition.None,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Box(
                Modifier
                    .background(Brush.verticalGradient(listOf(MapGradiStart, MapGradiEnd)))
                    .fillMaxWidth()
                    .height(126.dp)
            )
        }
    }
}
