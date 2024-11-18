package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.request.ExploreLocationAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreLocationAuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("users/places/distance")
    suspend fun postLocationAuth(
        @Body request: ExploreLocationAuthRequestDto,
    ): BaseResponse<ExploreLocationAuthResponseDto>
}
