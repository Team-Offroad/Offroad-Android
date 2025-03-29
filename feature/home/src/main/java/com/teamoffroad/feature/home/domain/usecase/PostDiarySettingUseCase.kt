package com.teamoffroad.feature.home.domain.usecase

import com.teamoffroad.feature.home.domain.repository.DiarySettingRepository

class PostDiarySettingUseCase(
    private val diarySettingRepository: DiarySettingRepository,
) {
    suspend operator fun invoke() {
        diarySettingRepository.postDiarySetting()
    }
}