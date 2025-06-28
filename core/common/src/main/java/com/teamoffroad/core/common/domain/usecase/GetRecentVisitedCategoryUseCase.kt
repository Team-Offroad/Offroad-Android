package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.core.common.domain.repository.QuestRepository
import javax.inject.Inject

class GetRecentVisitedCategoryUseCase
    @Inject
    constructor(
        private val repository: QuestRepository,
    ) {
        suspend operator fun invoke(): PlaceCategory =
            repository.getCategory()?.let {
                PlaceCategory.valueOf(it)
            } ?: PlaceCategory.NONE
    }
