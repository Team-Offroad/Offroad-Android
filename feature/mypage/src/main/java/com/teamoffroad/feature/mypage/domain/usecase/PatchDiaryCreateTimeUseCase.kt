package com.teamoffroad.feature.mypage.domain.usecase

import com.teamoffroad.feature.mypage.domain.repository.DiarySettingRepository

class PatchDiaryCreateTimeUseCase(
    private val diarySettingRepository: DiarySettingRepository,
) {
    suspend operator fun invoke(diaryTime: Int, meridiem: String) {
        val calculatedDiaryTime = when {
            meridiem == "AM" && diaryTime == MAX_TIME -> MIN_TIME
            meridiem == "AM" -> diaryTime
            meridiem == "PM" && diaryTime == MAX_TIME -> MAX_TIME
            meridiem == "PM" -> diaryTime + MAX_TIME
            else -> throw IllegalArgumentException(meridiem)
        }
        diarySettingRepository.patchDiaryCreateTime(calculatedDiaryTime)
    }

    companion object {
        const val MIN_TIME = 0
        const val MAX_TIME = 12
    }
}