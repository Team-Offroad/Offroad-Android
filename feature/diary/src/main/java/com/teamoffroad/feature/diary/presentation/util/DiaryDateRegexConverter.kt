package com.teamoffroad.feature.diary.presentation.util

import java.time.YearMonth

fun getYearMonthList(start: YearMonth, end: YearMonth): List<String> {
    return generateSequence(start) { current ->
        if (current < end) current.plusMonths(1) else null
    }.map { "${it.year}년 ${it.monthValue}월" }
        .toList()
}

fun getDiaryCalendarIndex(
    startYear: Int,
    currentDiaryCalendarPage: String,
): Int {
    val (year, month) = convertRegexToDate(currentDiaryCalendarPage)
    return (year - startYear) * 12 + (month - 1)
}

fun convertRegexToDate(moveDiaryCalendar: String): Pair<Int, Int> {
    val regex = Regex("(\\d+)년 (\\d+)월")
    val matchResult = regex.find(moveDiaryCalendar)

    return matchResult?.destructured?.let {
        it.component1().toInt() to it.component2().toInt()
    } ?: throw IllegalArgumentException()
}

fun convertDateToRegex(
    page: Int,
    startYear: Int,
): String {
    val year = startYear + (page / 12)
    val month = (page % 12) + 1
    return "${year}년 ${month}월"
}