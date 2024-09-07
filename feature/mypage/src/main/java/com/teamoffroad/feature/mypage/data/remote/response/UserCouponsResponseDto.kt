package com.teamoffroad.feature.mypage.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserCouponsResponseDto(
    @SerialName("availableCoupons")
    val availableCouponsDto: List<AvailableCouponsResponseDto>,

    @SerialName("usedCoupons")
    val usedCouponsDto: List<UsedCouponsResponseDto>
) {
    @Serializable
    data class AvailableCouponsResponseDto(
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

        @SerialName("placeId")
        val placeId: Int
    )

    @Serializable
    data class UsedCouponsResponseDto(
        @SerialName("name")
        val name: String,

        @SerialName("couponImageUrl")
        val couponImageUrl: String,
    )
}
