package com.teamoffroad.feature.recommendplace.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.recommendplace.data.remote.request.PlaceRecommendationsOrderRequestDto
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsOrderChatResponseDto
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsOrderResponseDto
import com.teamoffroad.feature.recommendplace.data.remote.response.PlaceRecommendationsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaceRecommendationsService {
    @GET("place-recommendations")
    suspend fun getPlaceRecommendations(): BaseResponse<PlaceRecommendationsResponseDto>

    @POST("place-recommendations/order")
    suspend fun postPlaceRecommendations(
        @Body data: PlaceRecommendationsOrderRequestDto
    ): BaseResponse<PlaceRecommendationsOrderResponseDto>

    @POST("place-recommendations/order/chat")
    suspend fun postPlaceRecommendationsOrderChat(
        @Body data: String
    ): BaseResponse<PlaceRecommendationsOrderChatResponseDto>
}