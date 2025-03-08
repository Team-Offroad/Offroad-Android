package com.teamoffroad.feature.diary.domain.repository

interface DiarySettingRepository {
    suspend fun getDiaryTutorialChecked(): Result<Boolean?>
    suspend fun patchDiaryTutorialChecked(): Result<Unit>
}