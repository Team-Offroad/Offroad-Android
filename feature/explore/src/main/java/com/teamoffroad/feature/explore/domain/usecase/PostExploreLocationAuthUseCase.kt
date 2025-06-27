package com.teamoffroad.feature.explore.domain.usecase

import com.teamoffroad.core.common.domain.model.PlaceCategory
import com.teamoffroad.core.common.domain.repository.QuestRepository
import com.teamoffroad.feature.explore.domain.model.ExploreLocationResult
import com.teamoffroad.feature.explore.domain.repository.UserRepository

class PostExploreLocationAuthUseCase(
    private val userRepository: UserRepository,
    private val questRepository: QuestRepository,
) {
    suspend operator fun invoke(
        placeId: Long,
        latitude: Double,
        longitude: Double,
        category: PlaceCategory,
    ): ExploreLocationResult {
        val result: ExploreLocationResult = userRepository.saveLocationAuth(placeId, latitude, longitude)

        if (result.isValidPosition && result.isFirstVisitToday) {
            questRepository.saveCompleteQuests(result.completeQuests)
            questRepository.saveCategory(category.name)
        }

        return result
    }
}
