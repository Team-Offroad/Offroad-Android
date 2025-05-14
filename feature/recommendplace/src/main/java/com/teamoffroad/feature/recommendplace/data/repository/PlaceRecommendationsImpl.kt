package com.teamoffroad.feature.recommendplace.data.repository

import com.teamoffroad.feature.recommendplace.data.mapper.toData
import com.teamoffroad.feature.recommendplace.data.mapper.toDomain
import com.teamoffroad.feature.recommendplace.data.remote.service.PlaceRecommendationsService
import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations
import com.teamoffroad.feature.recommendplace.domain.repository.PlaceRecommendationsRepository
import javax.inject.Inject

class PlaceRecommendationsImpl @Inject constructor(
    private val placeRecommendationsService: PlaceRecommendationsService
): PlaceRecommendationsRepository {
    override suspend fun fetchPlaceRecommendations(): PlaceRecommendations {
        val response = placeRecommendationsService.getPlaceRecommendations().data
        return response?.toData()?.toDomain() ?: PlaceRecommendations(emptyList())
    }
}