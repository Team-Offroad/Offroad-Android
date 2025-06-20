package com.teamoffroad.feature.recommendplace.domain.repository

import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsFixedPhrase
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChat
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChatRequest

interface PlaceRecommendationsRepository {
    suspend fun fetchPlaceRecommendations(): PlaceRecommendations

    suspend fun postPlaceRecommendationsOrder(order: PlaceRecommendationsOrder): String

    suspend fun postPlaceRecommendationsOrderChat(content: PlaceRecommendationsOrderChatRequest): PlaceRecommendationsOrderChat

    suspend fun fetchPlaceRecommendationsFixedPhrase(): PlaceRecommendationsFixedPhrase
}