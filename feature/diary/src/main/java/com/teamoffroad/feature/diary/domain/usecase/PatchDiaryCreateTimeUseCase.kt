package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class PatchDiaryCreateTimeUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(diaryTime: Int, meridiem: String) {
        val calculatedDiaryTime = when {
            meridiem == "AM" && diaryTime == MAX_TIME -> MIN_TIME
            meridiem == "AM" -> diaryTime
            meridiem == "PM" && diaryTime == MAX_TIME -> MAX_TIME
            meridiem == "PM" -> diaryTime + MAX_TIME
            else -> throw IllegalArgumentException(meridiem)
        }
        diaryRepository.patchDiaryCreateTime(calculatedDiaryTime)
    }

    companion object {
        const val MIN_TIME = 0
        const val MAX_TIME = 12
    }
}