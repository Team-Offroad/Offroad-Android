package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.repository.QuestRepository
import javax.inject.Inject

class GetCompleteQuestListUseCase
    @Inject
    constructor(
        private val repository: QuestRepository,
    ) {
        suspend operator fun invoke(): List<String> = repository.getCompleteQuests()
    }
