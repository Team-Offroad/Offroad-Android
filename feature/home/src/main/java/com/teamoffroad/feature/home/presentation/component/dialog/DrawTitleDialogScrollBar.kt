package com.teamoffroad.feature.home.presentation.component.dialog

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray100

fun Modifier.drawScrollbar(state: LazyListState): Modifier = this.then(
    Modifier.drawBehind {
        val totalItems = state.layoutInfo.totalItemsCount
        val firstVisibleItemIndex = state.firstVisibleItemIndex
        val visibleItems = state.layoutInfo.visibleItemsInfo.size
        val fraction = firstVisibleItemIndex.toFloat() / (totalItems - visibleItems).coerceAtLeast(1).toFloat()

        val scrollbarHeight = (size.height / totalItems) * visibleItems
        val scrollbarY = fraction * (size.height - scrollbarHeight)

        val scrollbarWidth = 6.dp.toPx()
        val cornerRadius = 13.dp.toPx()

        drawRoundRect(
            color = Gray100,
            topLeft = androidx.compose.ui.geometry.Offset(size.width + 6.dp.toPx(), scrollbarY),
            size = androidx.compose.ui.geometry.Size(scrollbarWidth, scrollbarHeight),
            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
        )
    }
)