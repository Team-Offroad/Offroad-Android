package com.teamoffroad.feature.mypage.data.remote.response

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class DeleteUserInfoResponseDto(
    val delete: BaseResponse<Unit>,
)
