package com.teamoffroad.feature.home.data.remote.service

import retrofit2.http.POST

interface DiarySettingService {

    @POST("diary/setting")
    suspend fun postDiarySetting()
}