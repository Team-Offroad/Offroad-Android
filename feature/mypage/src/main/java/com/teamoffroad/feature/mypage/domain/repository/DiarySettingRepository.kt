package com.teamoffroad.feature.mypage.domain.repository

interface DiarySettingRepository {
    suspend fun patchDiaryCreateTime(diaryTime: Int)
}