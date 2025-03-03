package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.teamoffroad.core.designsystem.theme.ListBg
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
fun Picker(
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

@Composable
fun DiaryTimePicker(
    updateDiaryTime: (Boolean, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        val hoursValue = remember {
            (1..12).map {
                if (it < 10) "  $it     00"
                else "$it    00"
            }
        }
        val hoursValuesPickerState = rememberPickerState()

        val meridiemValue = remember { listOf("AM", "PM") }
        val meridiemValuePickerState = rememberPickerState()

        LaunchedEffect(hoursValuesPickerState.selectedItem) {
            updateDiaryTime(true, hoursValuesPickerState.selectedItem)
        }

        LaunchedEffect(meridiemValuePickerState.selectedItem) {
            updateDiaryTime(false, meridiemValuePickerState.selectedItem)
        }

        Box {
            Box(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(7.dp),
                        color = ListBg
                    )
                    .height(36.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Picker(
                    pickerState = hoursValuesPickerState,
                    items = hoursValue,
                    visibleItemsCount = 3,
                    textStyle = OffroadTheme.typography.subtitleReg,
                    timeDivider = true,
                    width = 120.dp,
                    textModifier = Modifier.padding(4.dp),
                )
                Picker(
                    pickerState = meridiemValuePickerState,
                    items = meridiemValue,
                    visibleItemsCount = 3,
                    textStyle = OffroadTheme.typography.subtitleReg,
                    isInfinitelyScroll = false,
                    width = 44.dp,
                    textModifier = Modifier.padding(4.dp),
                )
            }
        }
    }
}