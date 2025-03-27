package com.teamoffroad.feature.diary.data.mapper

import com.teamoffroad.feature.diary.data.remote.response.DiaryFirstDateResponseDto
import com.teamoffroad.feature.diary.domain.model.DiaryFirstDate

fun DiaryFirstDateResponseDto.toDiaryFirstDate() =
    DiaryFirstDate(
        year = year,
        month = month,
        day = day,
    )