package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

interface UserService {
    @GET("users/me")
    suspend fun getMyPageUser(): BaseResponse<MyPageUserResponseDto>

    @GET("users/characters")
    suspend fun getCharacters(): BaseResponse<CharactersResponseDto>
}
    @PATCH("users/agree")
    suspend fun patchMarketingInfo(
        @Body marketing: MarketingInfoRequestDto,
    ): BaseResponse<MarketingInfoResponseDto>

    @POST("users/delete")
    suspend fun deleteUserInfo(
        @Body deleteCode: DeleteUserInfoRequestDto,
    ): BaseResponse<DeleteUserInfoResponseDto>
}
