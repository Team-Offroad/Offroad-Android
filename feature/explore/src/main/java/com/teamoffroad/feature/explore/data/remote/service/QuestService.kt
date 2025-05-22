package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.response.CourseQuestPlacesDto
import com.teamoffroad.feature.explore.data.remote.response.QuestsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuestService {
    @GET("quests")
    suspend fun getQuests(
        @Query("isActive") isActive: Boolean,
        @Query("cursor") cursor: Long,
        @Query("size") size: Int,
    ): BaseResponse<QuestsResponseDto>

    @GET("quests/course/{questId}")
    suspend fun getQuestCourse(
        @Query("questId") questId: Long,
    ): BaseResponse<CourseQuestPlacesDto>
}
