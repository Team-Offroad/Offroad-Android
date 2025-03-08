package com.teamoffroad.feature.mypage.data.remote.service

import retrofit2.http.PATCH
import retrofit2.http.Query

interface DiarySettingService {

    @PATCH("diary/setting/create-time")
    suspend fun patchDiaryCreateTime(
        @Query("hour")
        diaryTime: Int
    )
}