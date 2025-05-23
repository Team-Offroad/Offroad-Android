package com.teamoffroad.feature.recommendplace.domain.repository

import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder

interface PlaceRecommendationsRepository {
    suspend fun fetchPlaceRecommendations(): PlaceRecommendations

    suspend fun postPlaceRecommendationsOrder(order: PlaceRecommendationsOrder): String
}