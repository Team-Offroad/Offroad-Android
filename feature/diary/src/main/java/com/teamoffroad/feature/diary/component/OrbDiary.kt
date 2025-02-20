package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.DiaryButton
import com.teamoffroad.core.designsystem.theme.DiaryWeekItem
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.TooltipTitle
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.offroad.feature.diary.R
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun OrbDiary(
    modifier: Modifier = Modifier,
    currentDate: LocalDate = LocalDate.now(),
    yearRange: IntRange = IntRange(2025, 2100)
) {
    val initialPage = (currentDate.year - yearRange.first) * 12 + currentDate.monthValue - 1
    val pageCount = (yearRange.last - yearRange.first) * 12

    var currentYearAndMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableIntStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { pageCount })

    LaunchedEffect(pagerState.currentPage) {
        val monthCalculate = (pagerState.currentPage - currentPage).toLong()
        currentYearAndMonth = currentYearAndMonth.plusMonths(monthCalculate)
        currentPage = pagerState.currentPage
    }

    Column(
        modifier = modifier
            .padding(bottom = 46.dp)
            .background(color = Main1)
            .fillMaxSize(),
    ) {
        OrbDiaryHeader(
            modifier = Modifier
                .padding(top = 42.dp, bottom = 26.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            text = currentYearAndMonth
        )
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
        ) { page ->
            val date = LocalDate.of(
                yearRange.first + page / 12,
                page % 12 + 1,
                1
            )
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) {
                OrbDiaryItems(
                    modifier = Modifier,
                    currentDate = date,
                    onSelectedDate = {}
                )
            }
        }
    }
}

@Composable
fun OrbDiaryHeader(
    modifier: Modifier = Modifier,
    text: YearMonth,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = DiaryButton)
                .size(22.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_diary_previous),
                contentDescription = "previous"
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = 30.dp),
            text = text.year.toString() + "년 " + text.monthValue + "월",
            color = TooltipTitle,
            style = OffroadTheme.typography.tooltipTitle
        )
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = DiaryButton)
                .size(22.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_diary_next),
                contentDescription = "previous"
            )
        }
    }
}

@Composable
fun OrbDiaryItems(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit
) {
    val lastDay by remember { mutableIntStateOf(currentDate.lengthOfMonth()) }
    val firstDay by remember {
        mutableIntStateOf(
            if (currentDate.withDayOfMonth(1).dayOfWeek == java.time.DayOfWeek.SUNDAY) 1 else currentDate.withDayOfMonth(
                1
            ).dayOfWeek.value + 1
        )
    }
    val days by remember { mutableStateOf(IntRange(1, lastDay).toList()) }
    Column(
        modifier = modifier
            .padding(horizontal = 22.dp)
            .background(shape = RoundedCornerShape(18.dp), color = White),
    ) {
        WeekTitle(
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 30.dp)
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(bottom = 30.dp)
                .height(310.dp),
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1 until firstDay) {
                item {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 20.dp)
                    )
                }
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                OrbDiaryCell(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    date = date,
                    dateButtonClick = onSelectedDate
                )
            }
        }
    }
}

@Composable
fun OrbDiaryCell(
    modifier: Modifier = Modifier,
    date: LocalDate,
    dateButtonClick: (LocalDate) -> Unit
) {
    Box(
        modifier = modifier
            .clickableWithoutRipple { dateButtonClick(date) },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = BoxInfo)
                .size(30.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
                color = Stroke,
                style = OffroadTheme.typography.subtitle2Semibold
            )
        }
    }
}

@Composable
fun WeekTitle(
    modifier: Modifier = Modifier
) {
    val weekTitles = listOf(
        java.time.DayOfWeek.SUNDAY, java.time.DayOfWeek.MONDAY, java.time.DayOfWeek.TUESDAY,
        java.time.DayOfWeek.WEDNESDAY, java.time.DayOfWeek.THURSDAY, java.time.DayOfWeek.FRIDAY,
        java.time.DayOfWeek.SATURDAY
    )
    Row(modifier) {
        weekTitles.forEach { dayOfWeek ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                color = DiaryWeekItem,
                style = OffroadTheme.typography.btnSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}