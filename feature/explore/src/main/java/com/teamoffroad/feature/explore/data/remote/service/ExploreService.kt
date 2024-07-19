package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.request.ExploreLocationAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.request.ExploreQrAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreLocationAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreQrAuthResponseDto
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
    suspend fun authenticateQr(
        @Body request: ExploreQrAuthRequestDto,
    ): BaseResponse<ExploreQrAuthResponseDto>

    @POST("users/places/distance")
    suspend fun authenticateLocation(
        @Body request: ExploreLocationAuthRequestDto,
    ): BaseResponse<ExploreLocationAuthResponseDto>
}
