package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class GetDiaryFirstDateUseCase(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke() = diaryRepository.getDiaryFirstDate()
}