package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.request.ExploreLocationAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.request.ExploreQrAuthRequestDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreLocationAuthResponseDto
import com.teamoffroad.feature.explore.data.remote.response.ExploreQrAuthResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("users/adventures/authentication")
    suspend fun postQrAuth(
        @Body request: ExploreQrAuthRequestDto,
    ): BaseResponse<ExploreQrAuthResponseDto>

    @POST("users/places/distance")
    suspend fun postLocationAuth(
        @Body request: ExploreLocationAuthRequestDto,
    ): BaseResponse<ExploreLocationAuthResponseDto>
}
