package com.teamoffroad.feature.diary.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.diary.data.remote.response.DiaryTutorialCheckedResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH

interface DiarySettingService {

    @GET("diary/setting/tutorial-checked")
    suspend fun getDiaryTutorialChecked(): BaseResponse<DiaryTutorialCheckedResponseDto>

    @PATCH("diary/setting/tutorial-checked")
    suspend fun patchDiaryTutorialChecked(): BaseResponse<Unit?>
}