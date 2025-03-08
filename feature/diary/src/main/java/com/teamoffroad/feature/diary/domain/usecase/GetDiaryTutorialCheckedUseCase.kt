package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiarySettingRepository

class GetDiaryTutorialCheckedUseCase(
    private val diarySettingRepository: DiarySettingRepository,
) {
    suspend operator fun invoke() = diarySettingRepository.getDiaryTutorialChecked()
}