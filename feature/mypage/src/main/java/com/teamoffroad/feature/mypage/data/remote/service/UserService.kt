package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.CharactersResponseDto
import retrofit2.http.GET

interface UserService {

    @GET("users/characters")
    suspend fun getCharacters(): BaseResponse<CharactersResponseDto>
}
