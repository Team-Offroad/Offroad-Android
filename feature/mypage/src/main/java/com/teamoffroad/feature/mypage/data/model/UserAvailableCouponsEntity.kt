package com.teamoffroad.feature.mypage.data.model

data class UserAvailableCouponsEntity(
    val coupons: List<AvailableCouponsEntity>,
    val availableCouponsCount: Int,
    val usedCouponsCount: Int
) {
    data class AvailableCouponsEntity(
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val isNewGained: Boolean,
        val cursorId: Int,
    )
}
