package com.teamoffroad.feature.recommendplace.data.repository

import com.teamoffroad.feature.recommendplace.data.mapper.toData
import com.teamoffroad.feature.recommendplace.data.mapper.toDomain
import com.teamoffroad.feature.recommendplace.data.remote.request.PlaceRecommendationsOrderChatRequestDto
import com.teamoffroad.feature.recommendplace.data.remote.service.PlaceRecommendationsService
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsFixedPhrase
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChat
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChatRequest
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import javax.inject.Inject

class PlaceRecommendationsRepositoryImpl @Inject constructor(
    private val placeRecommendationsService: PlaceRecommendationsService
): PlaceRecommendationsRepository {
    override suspend fun fetchPlaceRecommendations(): PlaceRecommendations {
        val response = placeRecommendationsService.getPlaceRecommendations().data
        return response?.toData()?.toDomain() ?: PlaceRecommendations(emptyList())
    }

    override suspend fun postPlaceRecommendationsOrder(order: PlaceRecommendationsOrder): String {
        val response = placeRecommendationsService.postPlaceRecommendations(order.toData()).data
        return response?.content ?: ""
    }

    override suspend fun postPlaceRecommendationsOrderChat(content: PlaceRecommendationsOrderChatRequest): PlaceRecommendationsOrderChat {
        val response = placeRecommendationsService.postPlaceRecommendationsOrderChat(content.toData()).data
        return response?.toData()?.toDomain() ?: PlaceRecommendationsOrderChat("", false)
    }

    override suspend fun fetchPlaceRecommendationsFixedPhrase(): PlaceRecommendationsFixedPhrase {
        val response = placeRecommendationsService.getPlaceRecommendationsFixedPhrase().data
        return response?.toData() ?: PlaceRecommendationsFixedPhrase("")
    }
}