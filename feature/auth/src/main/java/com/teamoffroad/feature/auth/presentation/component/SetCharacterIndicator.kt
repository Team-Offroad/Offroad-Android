package com.teamoffroad.feature.auth.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.White

@Composable
fun SetCharacterIndicator(imageSize: Int, pagerState: PagerState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        for (indicator in 1..imageSize) {
            val indicatorOpacity = if (indicator == (pagerState.currentPage)%imageSize + 1) 1.0f else 0.4f
            Box(
                modifier = Modifier
                    .width(18.dp)
                    .height(4.dp)
                    .padding(horizontal = 3.dp)
                    .background(
                        White.copy(alpha = indicatorOpacity),
                        shape = RoundedCornerShape(14.dp)
                    )
            )
        }
    }
}