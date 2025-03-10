package com.teamoffroad.feature.diary.data.repository

import com.teamoffroad.feature.diary.data.mapper.toDiaryFirstDate
import com.teamoffroad.feature.diary.data.mapper.toHexCodeMap
import com.teamoffroad.feature.diary.data.remote.service.DiaryService
import com.teamoffroad.feature.diary.domain.model.DiaryFirstDate
import com.teamoffroad.feature.diary.domain.model.HexCode
import com.teamoffroad.feature.diary.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryService: DiaryService
) : DiaryRepository {

    override suspend fun getDiaryTutorialChecked(): Result<Boolean?> =
        runCatching { diaryService.getDiaryTutorialChecked().data?.value }

    override suspend fun patchDiaryTutorialChecked(): Result<Unit> =
        runCatching { diaryService.patchDiaryTutorialChecked() }

    override suspend fun getDiaryCreateTimeChecked(): Result<Boolean?> =
        runCatching { diaryService.getDiaryCreateTimeChecked().data?.value }

    override suspend fun patchDiaryCreateTimeChecked(): Result<Unit> =
        runCatching { diaryService.patchDiaryCreateTimeChecked() }

    override suspend fun getDiaryFirstDate(): Result<DiaryFirstDate?> =
        runCatching { diaryService.getDiaryFirstDate().data?.toDiaryFirstDate() }

    override suspend fun getDiaryMonthlyHex(
        year: Int,
        month: Int
    ): Result<Map<String, List<HexCode>>?> =
        runCatching {
            diaryService.getDiaryMonthlyHex(
                year = year,
                month = month
            ).data?.dailyHexCodes?.toHexCodeMap()
        }
}