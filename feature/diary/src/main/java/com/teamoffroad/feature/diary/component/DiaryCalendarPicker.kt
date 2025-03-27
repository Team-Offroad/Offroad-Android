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
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun DiaryCalendarPicker(
    currentDiaryCalendarPage: Int,
    diaryCalendarPickerState: PickerState,
    diaryFirstCreatedDate: Pair<Int, Int>,
    modifier: Modifier = Modifier,
) {
    val dummyStartDate = YearMonth.of(diaryFirstCreatedDate.first, diaryFirstCreatedDate.second)
    val dummyEndDate = YearMonth.of(LocalDate.now().year, LocalDate.now().month)
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
        NumberPicker(
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