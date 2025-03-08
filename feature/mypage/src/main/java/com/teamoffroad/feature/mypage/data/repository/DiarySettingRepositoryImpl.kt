package com.teamoffroad.feature.mypage.data.repository

import com.teamoffroad.feature.mypage.data.remote.service.DiarySettingService
import com.teamoffroad.feature.mypage.domain.repository.DiarySettingRepository
import javax.inject.Inject

class DiarySettingRepositoryImpl @Inject constructor(
    private val diarySettingService: DiarySettingService,
) : DiarySettingRepository {
    override suspend fun patchDiaryCreateTime(diaryTime: Int) {
        diarySettingService.patchDiaryCreateTime(diaryTime)
    }
}