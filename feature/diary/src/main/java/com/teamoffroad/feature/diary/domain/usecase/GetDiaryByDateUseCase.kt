package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.model.MemoryLightSetting
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
            .map { diaries ->
                var initialPage = 0
                val memoryLight = buildList {
                    addAll(diaries?.previousDiaries.orEmpty())
                    diaries?.targetDiary?.let {
                        initialPage = size
                        add(it)
                    }
                    addAll(diaries?.nextDiaries.orEmpty())
                }
                MemoryLightSetting(
                    initialPage = initialPage,
                    pageCount = memoryLight.size,
                    memoryLight = memoryLight
                )
            }
}