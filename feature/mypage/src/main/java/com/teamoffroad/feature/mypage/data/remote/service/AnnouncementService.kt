package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.AnnouncementResponseDto
import retrofit2.http.GET

interface AnnouncementService {
    @GET("announcement")
    suspend fun getAnnouncement(): BaseResponse<AnnouncementResponseDto>
}