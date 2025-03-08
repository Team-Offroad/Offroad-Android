package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.DiarySettingRepository

class PatchDiaryCreateTimeUseCase(
    private val diarySettingRepository: DiarySettingRepository,
) {
    suspend operator fun invoke(diaryTime: Int, meridiem: String) {
        val calculatedDiaryTime = when {
            meridiem == "AM" && diaryTime == 12 -> 0
            meridiem == "AM" -> diaryTime
            meridiem == "PM" && diaryTime == 12 -> 12
            meridiem == "PM" -> diaryTime + 12
            else -> throw IllegalArgumentException(meridiem)
        }
        diarySettingRepository.patchDiaryCreateTime(calculatedDiaryTime)
    }
}