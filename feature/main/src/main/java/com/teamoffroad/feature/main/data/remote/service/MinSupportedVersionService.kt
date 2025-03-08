package com.teamoffroad.feature.main.data.remote.service

import com.teamoffroad.feature.main.data.remote.response.MinSupportedVersionResponseDto
import retrofit2.http.GET

interface MinSupportedVersionService {
    @GET("app/min-supported-version")
    suspend fun getMinSupportedVersion(): MinSupportedVersionResponseDto
}
