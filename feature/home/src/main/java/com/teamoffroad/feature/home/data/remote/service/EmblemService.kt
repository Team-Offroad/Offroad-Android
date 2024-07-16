package com.teamoffroad.feature.home.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.home.data.remote.response.emblem.EmblemsResponseDto
import retrofit2.http.GET
import retrofit2.http.Header

interface EmblemService {
    @GET("users/emblems")
    suspend fun getEmblemsList(
        @Header("Authorization") Authorization: String
    ): BaseResponse<EmblemsResponseDto>
}