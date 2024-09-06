package com.teamoffroad.feature.mypage.data.model

data class UserCouponsEntity(
    val availableCouponsEntity: List<AvailableCouponsEntity>,
    val usedCouponsEntity: List<UsedCouponsEntity>
) {
    data class AvailableCouponsEntity(
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val isNewGained: Boolean,
        val placeId: Int
    )

    data class UsedCouponsEntity(
        val name: String,
        val couponImageUrl: String,
    )
}
