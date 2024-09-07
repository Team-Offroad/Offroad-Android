package com.teamoffroad.feature.mypage.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UseCouponRequestDto(
    @SerialName("code")
    val code: String,

    @SerialName("couponId")
    val couponId: Int,

    @SerialName("placeId")
    val placeId: Int,
)
