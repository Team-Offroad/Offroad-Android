package com.teamoffroad.feature.home.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.home.data.remote.response.EmblemsResponseDto
import com.teamoffroad.feature.home.data.remote.response.UserQuestsResponseDto
import com.teamoffroad.feature.home.data.remote.response.UsersAdventuresInformationsResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Query

interface UserService {
    @GET("users/emblems")
    suspend fun getEmblemsList(): BaseResponse<EmblemsResponseDto>

    @PATCH("users/emblems")
    suspend fun patchEmblem(
        @Query("emblemCode") emblemCode: String,
    ): BaseResponse<Unit>

    @GET("users/quests")
    suspend fun getUsersQuests(): BaseResponse<UserQuestsResponseDto>

    @GET("users/adventures/informations")
    suspend fun getAdventuresInformations(
        @Query("category") category: String
    ): BaseResponse<UsersAdventuresInformationsResponseDto>
}