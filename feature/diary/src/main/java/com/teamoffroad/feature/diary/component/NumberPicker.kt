package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun rememberPickerState() = remember { PickerState() }

class PickerState {
    var selectedItem by mutableStateOf("")
}

@Composable
fun NumberPicker(
    items: List<String>,
    pickerState: PickerState = rememberPickerState(),
    startIndex: Int = 0,
    visibleItemsCount: Int = 3,
    textStyle: TextStyle = LocalTextStyle.current,
    selectedTextStyle: TextStyle = OffroadTheme.typography.title,
    timeDivider: Boolean = false,
    width: Dp,
    isInfinitelyScroll: Boolean = true,
    isCalendar: Boolean = false,
    currentDiaryCalendarPage: Int = 0,
    textModifier: Modifier = Modifier,
    modifier: Modifier = Modifier,
) {
    val adjustedItems = if (!isInfinitelyScroll) {
        when (isCalendar) {
            true -> List(3) { null } + items + List(3) { null }
            false -> listOf(null) + items + listOf(null)
        }
    } else {
        items
    }
    val density = LocalDensity.current
    val visibleItemsMiddle = visibleItemsCount / 2

    val listScrollCount = if (isInfinitelyScroll) {
        Int.MAX_VALUE
    } else {
        adjustedItems.size
    }
    val listScrollMiddle = listScrollCount / 2
    val baseIndex = remember {
        if (isCalendar) currentDiaryCalendarPage
        else if (isInfinitelyScroll) {
            val offset = listScrollMiddle % adjustedItems.size
            listScrollMiddle - offset - visibleItemsMiddle
        } else {
            0
        }
    }
    val listStartIndex =
        remember { baseIndex + startIndex + if (!isCalendar && !isInfinitelyScroll) 1 else 0 }


    fun getItem(index: Int) = adjustedItems[index % adjustedItems.size]

    val listState = rememberLazyListState(initialFirstVisibleItemIndex = listStartIndex)
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    val itemHeight = with(density) {
        selectedTextStyle.fontSize.toDp() + PaddingValues(if (isCalendar) 4.dp else 8.dp).calculateTopPadding() +
                PaddingValues(if (isCalendar) 4.dp else 8.dp).calculateBottomPadding()
    }

    val fadingEdgeGradient = remember {
        Brush.verticalGradient(
            0f to Color.Transparent,
            0.5f to Main2,
            1f to Color.Transparent
        )
    }

    val selectedIndex by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex + visibleItemsMiddle
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .mapNotNull { index -> getItem(index + visibleItemsMiddle) }
            .distinctUntilChanged()
            .collect { item -> pickerState.selectedItem = item }
    }

    Box(
        modifier = modifier.padding(top = 1.dp)
    ) {
        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(width)
                .height(itemHeight * visibleItemsCount)
                .fadingEdge(fadingEdgeGradient),
        ) {
            items(listScrollCount) { index ->
                val isSelected = selectedIndex == index
                val currentItemText by remember {
                    mutableStateOf(if (getItem(index) == null) "" else getItem(index).toString())
                }

                Text(
                    text = currentItemText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = if (isSelected) selectedTextStyle else textStyle,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .height(itemHeight)
                        .then(textModifier),
                )
            }
        }

        if (timeDivider)
            Text(
                text = ":",
                color = Main2,
                style = OffroadTheme.typography.title,
                modifier = Modifier
                    .padding(start = 54.dp)
                    .padding(vertical = 41.dp),
            )
    }
}

private fun Modifier.fadingEdge(brush: Brush) = this
    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
    .drawWithContent {
        drawContent()
        drawRect(brush = brush, blendMode = BlendMode.DstIn)
    }