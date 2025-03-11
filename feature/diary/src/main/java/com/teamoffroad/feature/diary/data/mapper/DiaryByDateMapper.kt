package com.teamoffroad.feature.diary.data.mapper

import com.teamoffroad.feature.diary.data.remote.response.DiaryByDateResponseDto
import com.teamoffroad.feature.diary.data.remote.response.MemoryLightDto
import com.teamoffroad.feature.diary.domain.model.DiaryByDate
import com.teamoffroad.feature.diary.domain.model.MemoryLight

fun DiaryByDateResponseDto.toDiaryByDate() =
    DiaryByDate(
        targetDiary = targetDiary.toMemoryLight(),
        previousDiaries = previousDiaries.map { memoryLightDto ->
            memoryLightDto.toMemoryLight()
        },
        nextDiaries = nextDiaries.map { memoryLightDto ->
            memoryLightDto.toMemoryLight()
        }
    )

fun MemoryLightDto.toMemoryLight() =
    MemoryLight(
        id = id,
        dailyRecommend = dailyRecommend,
        content = content,
        year = year,
        month = month,
        day = day,
        summation = summation,
        hexCodes = hexCodes.map { entry ->
            entry.toHexCode()
        }
    )