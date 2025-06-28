package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.repository.QuestRepository

class GetQuestCourseUseCase(
    private val questRepository: QuestRepository,
) {
    suspend operator fun invoke(questId: Long) = questRepository.fetchQuestCourse(questId)
}
