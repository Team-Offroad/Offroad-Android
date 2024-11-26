package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserUsedCouponsResponseDto(
    @SerialName("coupons")
    val coupons: List<UsedCouponsResponseDto>,

    @SerialName("availableCouponsCount")
    val availableCouponsCount: Int,

    @SerialName("usedCouponsCount")
    val usedCouponsCount: Int
) {
    @Serializable
    data class UsedCouponsResponseDto(
        @SerialName("name")
        val name: String,

        @SerialName("couponImageUrl")
        val couponImageUrl: String,

        @SerialName("cursorId")
        val cursorId: Int,
    )
}
