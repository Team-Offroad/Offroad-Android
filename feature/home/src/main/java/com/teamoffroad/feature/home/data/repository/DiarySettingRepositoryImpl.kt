package com.teamoffroad.feature.home.data.repository

import com.teamoffroad.feature.home.data.remote.service.DiarySettingService
import com.teamoffroad.feature.home.domain.repository.DiarySettingRepository
import javax.inject.Inject

class DiarySettingRepositoryImpl @Inject constructor(
    private val diarySettingService: DiarySettingService,
) : DiarySettingRepository {
    override suspend fun postDiarySetting() {
        diarySettingService.postDiarySetting()
    }
}