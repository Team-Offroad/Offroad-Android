package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.DiarySettingRepository

class GetDiaryCheckLatestUseCase(
    private val diarySettingRepository: DiarySettingRepository
) {
    suspend operator fun invoke() = diarySettingRepository.getDiaryCheckLatest()
}