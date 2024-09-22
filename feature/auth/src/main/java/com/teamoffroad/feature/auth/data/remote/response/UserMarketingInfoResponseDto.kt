package com.teamoffroad.feature.auth.data.remote.response

import com.teamoffroad.core.common.data.remote.response.BaseResponse
import kotlinx.serialization.Serializable

@Serializable
data class UserMarketingInfoResponseDto(
    val marketing: BaseResponse<Boolean>
)