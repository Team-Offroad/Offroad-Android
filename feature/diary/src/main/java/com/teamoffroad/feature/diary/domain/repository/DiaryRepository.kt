package com.teamoffroad.feature.diary.domain.repository

import com.teamoffroad.feature.diary.domain.model.DiaryByDate
import com.teamoffroad.feature.diary.domain.model.DiaryFirstDate
import com.teamoffroad.feature.diary.domain.model.DiaryLatest
import com.teamoffroad.feature.diary.domain.model.HexCode

interface DiaryRepository {
    suspend fun getDiaryTutorialChecked(): Result<Boolean?>
    suspend fun patchDiaryTutorialChecked(): Result<Unit>
    suspend fun patchDiaryCreateTime(diaryTime: Int)
    suspend fun getDiaryCreateTimeChecked(): Result<Boolean?>
    suspend fun patchDiaryCreateTimeChecked(): Result<Unit>
    suspend fun getDiaryFirstDate(): Result<DiaryFirstDate?>
    suspend fun patchDiaryCheck(date: String): Result<Unit>
    suspend fun getDiaryMonthlyHex(year: Int, month: Int): Result<Map<String, List<HexCode>>?>
    suspend fun getDiaryLatest(previousCount: Int): Result<DiaryLatest?>
    suspend fun getDiaryCheckLatest(): Result<Boolean?>
    suspend fun getDiaryByDate(
        date: String,
        previousCount: Int,
        nextCount: Int
    ): Result<DiaryByDate?>
}