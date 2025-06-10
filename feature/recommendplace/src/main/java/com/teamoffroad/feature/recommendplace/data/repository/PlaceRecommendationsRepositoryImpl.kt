package com.teamoffroad.feature.recommendplace.data.repository

import com.teamoffroad.feature.recommendplace.data.mapper.toData
import com.teamoffroad.feature.recommendplace.data.mapper.toDomain
import com.teamoffroad.feature.recommendplace.data.remote.service.PlaceRecommendationsService
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrder
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendationsOrderChat
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

    override suspend fun postPlaceRecommendationsOrderChat(content: String): PlaceRecommendationsOrderChat {
        val response = placeRecommendationsService.postPlaceRecommendationsOrderChat(content).data
        return response?.toData()?.toDomain() ?: PlaceRecommendationsOrderChat("", false)
    }
}