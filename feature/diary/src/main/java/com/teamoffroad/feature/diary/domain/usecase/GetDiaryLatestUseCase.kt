package com.teamoffroad.feature.diary.domain.usecase

import com.teamoffroad.feature.diary.domain.model.MemoryLightSetting
import com.teamoffroad.feature.diary.domain.repository.DiaryRepository

class GetDiaryLatestUseCase(
    private val diaryRepository: DiaryRepository,
) {
    suspend operator fun invoke(previousCount: Int): Result<MemoryLightSetting> =
        diaryRepository.getDiaryLatest(previousCount).map { diaries ->
            var initialPage = 0
            val memoryLight = buildList {
                addAll(diaries?.previousDiaries.orEmpty())
                diaries?.latestDiary?.let {
                    initialPage = size
                    add(it)
                }
            }
            MemoryLightSetting(
                initialPage = initialPage,
                pageCount = memoryLight.size,
                memoryLight = memoryLight,
            )
        }
}