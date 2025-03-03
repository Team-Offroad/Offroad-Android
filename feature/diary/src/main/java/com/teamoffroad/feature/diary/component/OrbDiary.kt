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
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.teamoffroad.feature.diary.presentation.model.DiaryUiState
import com.teamoffroad.feature.diary.presentation.util.convertDateToRegex
import com.teamoffroad.feature.diary.presentation.util.convertRegexToDate
import com.teamoffroad.offroad.feature.diary.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun OrbDiary(
    currentDate: LocalDate = LocalDate.now(),
    diaryUiState: DiaryUiState,
    diaryCalendarInitPage: Int,
    diaryCalendarLastPage: Int,
    maxMonth: Int = 12,
    dateButtonClick: (String) -> Unit,
    diaryTitleClick: (Boolean) -> Unit,
    diaryMoveClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    ) {
    val coroutineScope = rememberCoroutineScope()
    val initialPage =
        (currentDate.year - diaryCalendarInitPage) * maxMonth + currentDate.monthValue - 1
    val pageCount = (diaryCalendarLastPage - diaryCalendarInitPage) * maxMonth

    var currentYearAndMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableIntStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { pageCount })

    LaunchedEffect(pagerState.currentPage) {
        val monthCalculate = (pagerState.currentPage - currentPage).toLong()
        currentYearAndMonth = currentYearAndMonth.plusMonths(monthCalculate)
        currentPage = pagerState.currentPage
    }

    LaunchedEffect(diaryUiState.currentDiaryCalendarPage) {
        val moveDiaryCalendar = diaryUiState.currentDiaryCalendarPage
        if (moveDiaryCalendar.isNotBlank()) {
            val (year, month) = convertRegexToDate(moveDiaryCalendar)
            coroutineScope.launch {
                val targetPage = (year - diaryCalendarInitPage) * maxMonth + (month - 1)
                pagerState.animateScrollToPage(targetPage)
            }
        }
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
            text = currentYearAndMonth,
            pagerState = pagerState,
            diaryCalendarInitPage = diaryCalendarInitPage,
            diaryTitleClick = diaryTitleClick,
            diaryMoveClick = diaryMoveClick,
            coroutineScope = coroutineScope,
        )
        HorizontalPager(
            modifier = Modifier,
            state = pagerState,
        ) { page ->
            val date = LocalDate.of(
                diaryCalendarInitPage + page / maxMonth,
                page % maxMonth + 1,
                1
            )
            if (page in pagerState.currentPage - 1..pagerState.currentPage + 1) {
                OrbDiaryItems(
                    modifier = Modifier,
                    currentDate = date,
                    dailyHexCodes = diaryUiState.dailyHexCodes,
                    dateButtonClick = dateButtonClick
                )
            }
        }
    }
}

@Composable
fun OrbDiaryHeader(
    modifier: Modifier = Modifier,
    text: YearMonth,
    pagerState: PagerState,
    diaryCalendarInitPage: Int,
    diaryTitleClick: (Boolean) -> Unit,
    diaryMoveClick: (String) -> Unit,
    coroutineScope: CoroutineScope,
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
                modifier = Modifier.clickableWithoutRipple {
                    coroutineScope.launch {
                        val previousPage = (pagerState.currentPage - 1).coerceAtLeast(0)
                        pagerState.animateScrollToPage(previousPage)
                        diaryMoveClick(convertDateToRegex(previousPage, diaryCalendarInitPage))
                    }
                },
                painter = painterResource(id = R.drawable.ic_diary_previous),
                contentDescription = "previous"
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .clickableWithoutRipple { diaryTitleClick(true) },
            text = text.year.toString() + stringResource(R.string.diary_year) + " " +
                    text.monthValue + stringResource(R.string.diary_month),
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
                modifier = Modifier.clickableWithoutRipple {
                    coroutineScope.launch {
                        val nextPage =
                            (pagerState.currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
                        pagerState.animateScrollToPage(nextPage)
                        diaryMoveClick(convertDateToRegex(nextPage, diaryCalendarInitPage))
                    }
                },
                painter = painterResource(id = R.drawable.ic_diary_next),
                contentDescription = "next"
            )
        }
    }
}

@Composable
fun OrbDiaryItems(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    dailyHexCodes: Map<Int, List<String>>,
    dateButtonClick: (String) -> Unit
) {
    val lastDay by remember { mutableIntStateOf(currentDate.lengthOfMonth()) }
    val firstDay by remember { mutableIntStateOf(currentDate.withDayOfMonth(1).dayOfWeek.value % 7 + 1) }
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
                    dailyHexCodes = dailyHexCodes,
                    dateButtonClick = dateButtonClick
                )
            }
        }
    }
}

@Composable
fun OrbDiaryCell(
    modifier: Modifier = Modifier,
    date: LocalDate,
    dailyHexCodes: Map<Int, List<String>>,
    dateButtonClick: (String) -> Unit
) {
    val backgroundColor = if (dailyHexCodes.containsKey(date.dayOfMonth)) {
        Brush.linearGradient(
            colors = dailyHexCodes[date.dayOfMonth]!!.mapIndexed { index, hex ->
                Color(android.graphics.Color.parseColor(if (hex.startsWith("#")) hex else "#$hex"))
                    .copy(alpha = if (index < 2) 0.7f else 1.0f)
            }
        )
    } else {
        SolidColor(BoxInfo)
    }

    Box(
        modifier = modifier
            .clickableWithoutRipple {
                if (backgroundColor != SolidColor(BoxInfo)) dateButtonClick(
                    date.toString()
                )
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(backgroundColor)
                .size(30.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier,
                textAlign = TextAlign.Center,
                text = date.dayOfMonth.toString(),
                color = if (dailyHexCodes.containsKey(date.dayOfMonth)) White else Stroke,
                style = OffroadTheme.typography.subtitle2Semibold
            )
        }
    }
}

@Composable
fun WeekTitle(
    modifier: Modifier = Modifier
) {
    val weekTitles = DayOfWeek.entries.sortedBy { it.value % 7 }
    Row(modifier) {
        weekTitles.forEach { days ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = days.getDisplayName(TextStyle.NARROW, Locale.KOREAN),
                color = DiaryWeekItem,
                style = OffroadTheme.typography.btnSmall,
                textAlign = TextAlign.Center
            )
        }
    }
}
