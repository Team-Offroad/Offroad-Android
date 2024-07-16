package com.teamoffroad.feature.home.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.home.data.remote.response.EmblemsResponseDto
import com.teamoffroad.feature.home.data.remote.response.UserQuestsResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query

interface EmblemService {
    @GET("users/emblems")
    suspend fun getEmblemsList(): BaseResponse<EmblemsResponseDto>

    @PATCH("users/emblems")
    suspend fun patchEmblem(
        @Query("emblemCode") emblemCode: String,
    ): BaseResponse<Unit>

    @GET("users/quests")
    suspend fun getUsersQuests(): BaseResponse<UserQuestsResponseDto>
}