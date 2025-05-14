package com.teamoffroad.feature.recommendplace.domain.repository

import com.teamoffroad.feature.recommendplace.domain.model.PlaceRecommendations

interface PlaceRecommendationsRepository {
    suspend fun fetchPlaceRecommendations(): PlaceRecommendations
}