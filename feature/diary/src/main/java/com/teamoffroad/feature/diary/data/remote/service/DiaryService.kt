package com.teamoffroad.feature.diary.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.diary.data.remote.response.DiaryByDateResponseDto
import com.teamoffroad.feature.diary.data.remote.response.DiaryCheckLatestResponseDto
import com.teamoffroad.feature.diary.data.remote.response.DiaryCreateTimeCheckedResponseDto
import com.teamoffroad.feature.diary.data.remote.response.DiaryFirstDateResponseDto
import com.teamoffroad.feature.diary.data.remote.response.DiaryLatestResponseDto
import com.teamoffroad.feature.diary.data.remote.response.DiaryMonthlyHexResponseDto
import com.teamoffroad.feature.diary.data.remote.response.DiaryTutorialCheckedResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface DiaryService {

    @GET("diary/setting/tutorial-checked")
    suspend fun getDiaryTutorialChecked(): BaseResponse<DiaryTutorialCheckedResponseDto>

    @PATCH("diary/setting/tutorial-checked")
    suspend fun patchDiaryTutorialChecked(): BaseResponse<Unit?>

    @PATCH("diary/setting/create-time")
    suspend fun patchDiaryCreateTime(
        @Query("hour")
        diaryTime: Int
    )

    @GET("diary/setting/create-time-checked")
    suspend fun getDiaryCreateTimeChecked(): BaseResponse<DiaryCreateTimeCheckedResponseDto>

    @PATCH("diary/setting/create-time-checked")
    suspend fun patchDiaryCreateTimeChecked(): BaseResponse<Unit?>

    @GET("diary/first-date")
    suspend fun getDiaryFirstDate(): BaseResponse<DiaryFirstDateResponseDto>

    @PATCH("diary/check")
    suspend fun patchDiaryCheck(
        @Query("date")
        date: String,
    ): BaseResponse<Unit>

    @GET("diary/monthly-hex")
    suspend fun getDiaryMonthlyHex(
        @Query("year")
        year: Int,
        @Query("month")
        month: Int,
    ): BaseResponse<DiaryMonthlyHexResponseDto>

    @GET("diary/latest")
    suspend fun getDiaryLatest(
        @Query("previousCount")
        previousCount: Int,
    ): BaseResponse<DiaryLatestResponseDto>

    @GET("diary/check-latest")
    suspend fun getDiaryCheckLatest(): BaseResponse<DiaryCheckLatestResponseDto>

    @GET("diary/by-date")
    suspend fun getDiaryByDate(
        @Query("date")
        date: String,
        @Query("previousCount")
        previousCount: Int,
        @Query("nextCount")
        nextCount: Int,
    ): BaseResponse<DiaryByDateResponseDto>
}