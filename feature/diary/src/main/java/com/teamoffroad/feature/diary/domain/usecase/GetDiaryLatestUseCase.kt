package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class GetDiaryLatestUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(previousCount: Int) =
        diaryRepository.getDiaryLatest(previousCount).map {
            it?.previousDiaries.orEmpty() + listOfNotNull(it?.latestDiary)
        }
}