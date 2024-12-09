package com.teamoffroad.feature.home.presentation.component.dialog

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100
import com.teamoffroad.feature.home.presentation.HomeViewModel.Companion.MIN_SHOWN_EMBLEM_DIALOG
import kotlinx.coroutines.delay

@Composable
fun Modifier.drawScrollbar(state: LazyListState): Modifier {
    val totalItems = state.layoutInfo.totalItemsCount
    var debouncedFirstVisibleItemIndex by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(state.firstVisibleItemIndex) {
        delay(50)
        debouncedFirstVisibleItemIndex = state.firstVisibleItemIndex.toFloat()
    }

    val fraction by animateFloatAsState(
        targetValue = debouncedFirstVisibleItemIndex / (totalItems - state.layoutInfo.visibleItemsInfo.size).coerceAtLeast(
            1
        ).toFloat(),
        animationSpec = SpringSpec(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessLow
        ), label = "fraction"
    )

    return if (totalItems < MIN_SHOWN_EMBLEM_DIALOG) {
        this
    } else {
        this.then(
            Modifier.drawBehind {
                val scrollbarHeight = (size.height / totalItems.coerceAtLeast(1)) * 2
                val scrollbarY = fraction * (size.height - scrollbarHeight)

                val scrollbarWidth = 6.dp.toPx()
                val cornerRadius = 13.dp.toPx()

                drawRoundRect(
                    color = Gray100,
                    topLeft = Offset(size.width + 6.dp.toPx(), scrollbarY),
                    size = Size(scrollbarWidth, scrollbarHeight),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                )
            }
        )
    }
}

