package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class PatchDiaryCheckUseCase(
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(date: String) {
        diaryRepository.patchDiaryCheck(date)
    }
}