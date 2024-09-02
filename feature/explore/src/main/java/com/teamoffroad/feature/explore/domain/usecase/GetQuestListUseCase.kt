package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.feature.explore.domain.model.Quest
import com.teamoffroad.feature.explore.domain.repository.QuestRepository

class GetQuestListUseCase(
    private val questRepository: QuestRepository,
) {

    suspend operator fun invoke(isProceeding: Boolean): List<Quest> {
        return questRepository.fetchQuests(isProceeding)
    }
}