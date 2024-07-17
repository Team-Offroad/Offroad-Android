package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.request.ExploreAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.PlacesResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ExploreService {

    @GET("places")
    suspend fun getPlaces(
        @Query("currentLatitude") currentLatitude: Double,
        @Query("currentLongitude") currentLongitude: Double,
    ): BaseResponse<PlacesResponseDto>

    @POST("users/adventures/authentication")
    suspend fun authenticateUser(
        @Body request: ExploreAuthRequestDto,
    ): BaseResponse<ExploreAuthResponseDto>
}