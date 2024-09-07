package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UseCouponResponseDto(
    @SerialName("success")
    val success: Boolean,
)
