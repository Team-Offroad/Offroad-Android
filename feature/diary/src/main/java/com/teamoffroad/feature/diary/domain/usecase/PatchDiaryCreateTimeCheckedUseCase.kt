package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class PatchDiaryCreateTimeCheckedUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke() = diaryRepository.patchDiaryCreateTimeChecked()
}