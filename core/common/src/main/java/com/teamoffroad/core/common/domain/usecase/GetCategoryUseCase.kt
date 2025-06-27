package com.teamoffroad.core.common.domain.usecase

import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.core.common.domain.repository.QuestRepository
import javax.inject.Inject

class GetCategoryUseCase
    @Inject
    constructor(
        private val repository: QuestRepository,
    ) {
        suspend operator fun invoke(): PlaceCategory = PlaceCategory.valueOf(repository.getCategory())
    }
