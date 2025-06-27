package com.teamoffroad.feature.explore.presentation.component

import android.view.MotionEvent.ACTION_CANCEL
import android.view.MotionEvent.ACTION_DOWN
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import com.teamoffroad.feature.explore.presentation.model.CourseQuestPlaceUiModel
import java.time.LocalDateTime

@Composable
fun CourseQuestPlacesContainer(
    places: List<CourseQuestPlaceUiModel>,
    deadline: String,
    dDay: Int,
    contentPadding: PaddingValues,
    mapHeight: Dp,
    isTouchingMapArea: MutableState<Boolean>,
    mapHeightPx: MutableFloatState,
    onVisitClick: (CourseQuestPlaceUiModel) -> Unit,
) {
    val listState = rememberLazyListState()
    val scrollOffset =
        remember {
            derivedStateOf {
                val firstIndex = listState.firstVisibleItemIndex
                val offset = listState.firstVisibleItemScrollOffset
                if (firstIndex == 0) offset.toFloat() else mapHeightPx.floatValue
            }
        }

    LazyColumn(
        state = listState,
        userScrollEnabled = mapHeightPx.floatValue != 0f && !isTouchingMapArea.value,
        contentPadding = contentPadding,
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(
                    object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset,
                            source: NestedScrollSource,
                        ) = if (isTouchingMapArea.value) available else Offset.Zero
                    },
                ).pointerInteropFilter { event ->
                    val shownMapHeight = mapHeightPx.floatValue - scrollOffset.value
                    when (event.actionMasked) {
                        ACTION_DOWN, ACTION_MOVE -> isTouchingMapArea.value = event.y < shownMapHeight
                        ACTION_UP, ACTION_CANCEL -> isTouchingMapArea.value = false
                    }
                    false
                },
    ) {
        item { Spacer(modifier = Modifier.height(mapHeight)) }
        item {
            DeadlineHeader(
                deadline = LocalDateTime.parse(deadline),
                dDay = "D-$dDay",
            )
        }
        itemsIndexed(places) { index, place ->
            CourseQuestPlaceItem(
                quest = place,
                isFirst = index == 0,
                isLast = index == places.lastIndex,
                onVisitClick = { onVisitClick(place) },
            )
        }
    }
}
