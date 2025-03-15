package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.DiaryCheckLatestResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface DiarySettingService {

    @PATCH("diary/setting/create-time")
    suspend fun patchDiaryCreateTime(
        @Query("hour")
        diaryTime: Int
    )

    @GET("diary/check-latest")
    suspend fun getDiaryCheckLatest(): BaseResponse<DiaryCheckLatestResponseDto>
}