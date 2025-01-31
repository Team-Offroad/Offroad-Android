package com.teamoffroad.feature.auth.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.auth.data.remote.request.UserMarketingInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.request.ProfileUpdateRequestDto
import com.teamoffroad.feature.auth.data.remote.request.SignInInfoRequestDto
import com.teamoffroad.feature.auth.data.remote.response.CharactersResponseDto
import com.teamoffroad.feature.auth.data.remote.response.DuplicateNicknameResponseDto
import com.teamoffroad.feature.auth.data.remote.response.UserMarketingInfoResponseDto
import com.teamoffroad.feature.auth.data.remote.response.SettingCharacterResponseDto
import com.teamoffroad.feature.auth.data.remote.response.SignInInfoResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthService {

    @POST("oauth/login")
    suspend fun postSignInInfo(
        @Body request: SignInInfoRequestDto,
    ): BaseResponse<SignInInfoResponseDto>

    @GET("users/nickname/check")
    suspend fun getDuplicateNickname(
        @Query("nickname") nickname: String,
    ): BaseResponse<DuplicateNicknameResponseDto>

    @GET("characters")
    suspend fun getCharacters(): BaseResponse<CharactersResponseDto>

    @POST("users/characters/{characterId}")
    suspend fun setCharacter(
        @Path("characterId") characterId: Int,
    ): BaseResponse<SettingCharacterResponseDto>

    @PATCH("users/profiles")
    suspend fun fetchUserProfile(
        @Body request: ProfileUpdateRequestDto,
    ): BaseResponse<SettingCharacterResponseDto>

    @PATCH("users/agree")
    suspend fun patchMarketingInfo(
        @Body marketing: UserMarketingInfoRequestDto,
    ): BaseResponse<UserMarketingInfoResponseDto>
}
