package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class GetDiaryMonthlyHexUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(year: Int, month: Int) =
        diaryRepository.getDiaryMonthlyHex(year = year, month = month)
}