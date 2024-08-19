package com.teamoffroad.feature.explore.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.explore.data.remote.request.QuestsRequestDto
import com.teamoffroad.feature.explore.data.remote.response.QuestsResponseDto
import retrofit2.http.Body
import retrofit2.http.GET

interface QuestService {

    @GET("quests")
    suspend fun getQuests(
        @Body request: QuestsRequestDto,
    ): BaseResponse<QuestsResponseDto>
}
