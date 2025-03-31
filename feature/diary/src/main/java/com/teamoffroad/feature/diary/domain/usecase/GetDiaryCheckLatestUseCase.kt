package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class GetDiaryCheckLatestUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke() = diaryRepository.getDiaryCheckLatest()
}