package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCouponsResponseDto(
    @SerialName("coupons")
    val coupons: List<CouponsResponseDto>,

    @SerialName("availableCouponsCount")
    val availableCouponsCount: Int,

    @SerialName("usedCouponsCount")
    val usedCouponsCount: Int
) {
    @Serializable
    data class CouponsResponseDto(
        @SerialName("id")
        val id: Int,

        @SerialName("name")
        val name: String,

        @SerialName("couponImageUrl")
        val couponImageUrl: String,

        @SerialName("description")
        val description: String,

        @SerialName("isNewGained")
        val isNewGained: Boolean,

        @SerialName("cursorId")
        val cursorId: Int,
    )
}
