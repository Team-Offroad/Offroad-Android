package com.teamoffroad.feature.home.data.remote.service

import com.teamoffroad.feature.home.data.remote.response.DummyUserResponseDto
import retrofit2.http.GET

interface DummyUserService {

    @GET("users")
    suspend fun getUsers(): List<DummyUserResponseDto>
}
