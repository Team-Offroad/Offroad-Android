package com.teamoffroad.feature.recommendplace.domain.repository

import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChat

interface PlaceRecommendationsRepository {
    suspend fun fetchPlaceRecommendations(): PlaceRecommendations

    suspend fun postPlaceRecommendationsOrder(order: PlaceRecommendationsOrder): String

    suspend fun postPlaceRecommendationsOrderChat(content: String): PlaceRecommendationsOrderChat
}