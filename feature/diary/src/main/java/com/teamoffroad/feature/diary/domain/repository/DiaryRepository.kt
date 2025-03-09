package com.teamoffroad.feature.diary.domain.repository

import com.teamoffroad.feature.diary.domain.model.DiaryFirstDate

interface DiaryRepository {
    suspend fun getDiaryTutorialChecked(): Result<Boolean?>
    suspend fun patchDiaryTutorialChecked(): Result<Unit>
    suspend fun getDiaryCreateTimeChecked(): Result<Boolean?>
    suspend fun patchDiaryCreateTimeChecked(): Result<Unit>
    suspend fun getDiaryFirstDate(): Result<DiaryFirstDate?>
}