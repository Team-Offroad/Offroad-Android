package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class GetDiaryByDateUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(date: String, previousCount: Int, nextCount: Int) =
        diaryRepository.getDiaryByDate(
            date = date,
            previousCount = previousCount,
            nextCount = nextCount
        )
            .map { it?.previousDiaries.orEmpty() + listOfNotNull(it?.targetDiary) + it?.nextDiaries.orEmpty() }
}