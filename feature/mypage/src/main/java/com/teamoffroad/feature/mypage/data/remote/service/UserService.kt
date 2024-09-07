package com.teamoffroad.feature.mypage.data.remote.service

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import com.teamoffroad.feature.mypage.data.remote.request.DeleteUserInfoRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.DeleteUserInfoResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.MyPageUserResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("users/me")
    suspend fun getMyPageUser(): BaseResponse<MyPageUserResponseDto>

    @POST("users/delete")
    suspend fun deleteUserInfo(
        @Body deleteCode: DeleteUserInfoRequestDto,
    ): BaseResponse<DeleteUserInfoResponseDto>
}