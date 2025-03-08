package com.teamoffroad.feature.diary.data.repository

import com.teamoffroad.feature.diary.data.remote.service.DiarySettingService
import com.teamoffroad.feature.diary.domain.repository.DiarySettingRepository
import javax.inject.Inject

class DiarySettingRepositoryImpl @Inject constructor(
    private val diarySettingService: DiarySettingService
) : DiarySettingRepository {

    override suspend fun getDiaryTutorialChecked(): Result<Boolean?> =
        runCatching { diarySettingService.getDiaryTutorialChecked().data?.value }

    override suspend fun patchDiaryTutorialChecked(): Result<Unit> =
        runCatching { diarySettingService.patchDiaryTutorialChecked() }
}