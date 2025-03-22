package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.diary.presentation.util.getYearMonthList
import com.teamoffroad.feature.mypage.presentation.component.Picker
import com.teamoffroad.feature.mypage.presentation.component.PickerState
import java.time.YearMonth

@Composable
fun DiaryCalendarPicker(
    currentDiaryCalendarPage: Int,
    diaryCalendarPickerState: PickerState,
    modifier: Modifier = Modifier,
) {
    val dummyStartDate = YearMonth.of(2025, 1)
    val dummyEndDate = YearMonth.of(2100, 12)
    val diaryCalendar = remember {
        getYearMonthList(dummyStartDate, dummyEndDate)
    }

    Box(modifier = modifier.padding(bottom = 50.dp)) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .background(
                    shape = RoundedCornerShape(7.dp),
                    color = ListBg
                )
                .height(36.dp)
                .align(Alignment.Center)
                .fillMaxWidth(),
        )
        Picker(
            pickerState = diaryCalendarPickerState,
            items = diaryCalendar,
            visibleItemsCount = 7,
            width = 130.dp,
            isInfinitelyScroll = false,
            isCalendar = true,
            currentDiaryCalendarPage = currentDiaryCalendarPage,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}