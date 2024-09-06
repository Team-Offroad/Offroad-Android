package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.response.PlacesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("places")
    suspend fun getPlaces(
        @Query("currentLatitude") currentLatitude: Double,
        @Query("currentLongitude") currentLongitude: Double,
        @Query("limit") limit: Int,
        @Query("isBounded") isBounded: Boolean,
    ): BaseResponse<PlacesResponseDto>
}
