package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.response.CharacterDetailResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") characterId: Int,
    ): BaseResponse<CharacterDetailResponseDto>
}
