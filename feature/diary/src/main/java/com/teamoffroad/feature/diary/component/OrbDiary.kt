package com.teamoffroad.feature.diary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.teamoffroad.core.designsystem.component.clickableWithoutRipple
import com.teamoffroad.core.designsystem.theme.BoxInfo
import com.teamoffroad.core.designsystem.theme.DiaryButton
import com.teamoffroad.core.designsystem.theme.DiaryWeekItem
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Stroke
import com.teamoffroad.core.designsystem.theme.TooltipTitle
import com.teamoffroad.core.designsystem.theme.White
import com.teamoffroad.feature.diary.domain.model.HexCode
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
    diaryFirstCreatedDate: Pair<Int, Int>,
    maxMonth: Int = 12,
    dateButtonClick: (String) -> Unit,
    diaryTitleClick: (Boolean) -> Unit,
    diaryMoveClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val initialPage =
        (currentDate.year - diaryFirstCreatedDate.first) * maxMonth + currentDate.monthValue - diaryFirstCreatedDate.second
    val pageCount =
        (currentDate.year - diaryFirstCreatedDate.first) * 12 + currentDate.monthValue - diaryFirstCreatedDate.second + 1

    var currentYearAndMonth by remember { mutableStateOf(YearMonth.now()) }
    var currentPage by remember { mutableIntStateOf(initialPage) }
    val pagerState = rememberPagerState(initialPage = initialPage, pageCount = { pageCount })

    LaunchedEffect(diaryUiState.currentDiaryCalendarPage) {
        val (year, month) = convertRegexToDate(diaryUiState.currentDiaryCalendarPage)
        val newPage =
            (year - diaryFirstCreatedDate.first) * maxMonth + month - diaryFirstCreatedDate.second
        if (newPage in 0 until pageCount) {
            coroutineScope.launch {
                pagerState.scrollToPage(newPage)
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        val monthCalculate = pagerState.currentPage - currentPage
        if (monthCalculate != 0) {
            currentYearAndMonth = currentYearAndMonth.plusMonths(monthCalculate.toLong())
            currentPage = pagerState.currentPage
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
            text = diaryUiState.currentDiaryCalendarPage,
            pagerState = pagerState,
            diaryCalendarInitPage = diaryFirstCreatedDate,
            lastDate = pageCount,
            diaryTitleClick = diaryTitleClick,
            diaryMoveClick = diaryMoveClick,
            coroutineScope = coroutineScope,
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier,
        ) { page ->
            val date = LocalDate.of(
                diaryFirstCreatedDate.first + (diaryFirstCreatedDate.second - 1 + page) / maxMonth,
                (diaryFirstCreatedDate.second - 1 + page) % maxMonth + 1,
                1
            )
            val rememberedHexCodes = remember(diaryUiState.dailyHexCodes) { diaryUiState.dailyHexCodes }

            OrbDiaryItems(
                    modifier = Modifier,
                    currentDate = date,
                    dailyHexCodes = rememberedHexCodes,
                    dateButtonClick = dateButtonClick
                )
        }
    }
}

@Composable
fun OrbDiaryHeader(
    modifier: Modifier = Modifier,
    text: String,
    pagerState: PagerState,
    diaryCalendarInitPage: Pair<Int, Int>,
    lastDate: Int,
    diaryTitleClick: (Boolean) -> Unit,
    diaryMoveClick: (String) -> Unit,
    coroutineScope: CoroutineScope,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
    ) {
        if (pagerState.currentPage != 0) {
            DiaryMoveMonthButton(
                image = painterResource(id = R.drawable.ic_diary_previous),
                diaryMoveClick = {
                    coroutineScope.launch {
                        val previousPage = (pagerState.currentPage - 1).coerceAtLeast(0)
                        pagerState.animateScrollToPage(previousPage)
                        diaryMoveClick(
                            convertDateToRegex(
                                page = previousPage,
                                startDate = diaryCalendarInitPage
                            )
                        )
                    }
                }
            )
        } else {
            Spacer(modifier = Modifier.size(22.dp))
        }
        Text(
            modifier = Modifier
                .width(180.dp)
                .padding(horizontal = 30.dp)
                .clickableWithoutRipple { diaryTitleClick(true) },
            text = text,
            color = TooltipTitle,
            style = OffroadTheme.typography.tooltipTitle,
            textAlign = TextAlign.Center,
        )
        if (pagerState.currentPage != lastDate - 1) {
            DiaryMoveMonthButton(
                image = painterResource(id = R.drawable.ic_diary_next),
                diaryMoveClick = {
                    coroutineScope.launch {
                        val nextPage =
                            (pagerState.currentPage + 1).coerceAtMost(pagerState.pageCount - 1)
                        pagerState.animateScrollToPage(nextPage)
                        diaryMoveClick(
                            convertDateToRegex(
                                page = nextPage,
                                startDate = diaryCalendarInitPage
                            )
                        )
                    }
                }
            )
        } else {
            Spacer(modifier = Modifier.size(22.dp))
        }
    }
}

@Composable
fun OrbDiaryItems(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    dailyHexCodes: Map<String, List<HexCode>>?,
    dateButtonClick: (String) -> Unit
) {
    val lastDay = currentDate.lengthOfMonth()
    val firstDay = currentDate.withDayOfMonth(1).dayOfWeek.value % 7 + 1
    val days = remember(lastDay) { IntRange(1, lastDay).toList() }

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
            columns = GridCells.Fixed(7),
            userScrollEnabled = false
        ) {
            items(firstDay - 1) {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .padding(top = 20.dp)
                )
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val currentDay = date.dayOfMonth.toString()
                val hexCode = dailyHexCodes?.get(currentDay)
                OrbDiaryCell(
                    modifier = Modifier
                        .padding(top = 20.dp),
                    date = date,
                    hexCode = hexCode,
                    dateButtonClick = dateButtonClick
                )
            }
        }
    }
}

@Composable
private fun OrbDiaryCell(
    modifier: Modifier = Modifier,
    date: LocalDate,
    hexCode: List<HexCode>?,
    dateButtonClick: (String) -> Unit
) {
    val backgroundColor = remember(hexCode) {
        if (!hexCode.isNullOrEmpty()) {
            Brush.linearGradient(
                colors = listOf(
                    Color(hexCode[0].small.toColorInt()),
                    Color(hexCode[0].large.toColorInt()),
                )
            )
        } else {
            SolidColor(BoxInfo)
        }
    }

    Box(
        modifier = modifier
            .clickableWithoutRipple {
                if (backgroundColor != SolidColor(BoxInfo)) dateButtonClick(date.toString())
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
                color = if (!hexCode.isNullOrEmpty()) White else Stroke,
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

@Composable
fun DiaryMoveMonthButton(
    image: Painter,
    diaryMoveClick: () -> Unit,
    modifier: Modifier = Modifier,
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
                diaryMoveClick()
            },
            painter = image,
            contentDescription = "previous"
        )
    }
}