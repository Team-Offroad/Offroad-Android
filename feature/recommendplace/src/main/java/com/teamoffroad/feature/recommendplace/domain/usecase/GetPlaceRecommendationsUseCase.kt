package com.teamoffroad.feature.recommendplace.domain.usecase

import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository

class GetPlaceRecommendationsUseCase(
    private val placeRecommendationsRepository: PlaceRecommendationsRepository
) {
    suspend fun fetchPlaceRecommendations(): PlaceRecommendations {
        return placeRecommendationsRepository.fetchPlaceRecommendations()
    }
}