package com.teamoffroad.feature.recommendplace.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsResponseDto
import retrofit2.http.GET

interface PlaceRecommendationsService {
    @GET("place-recommendations")
    suspend fun getPlaceRecommendations(): BaseResponse<PlaceRecommendationsResponseDto>
}