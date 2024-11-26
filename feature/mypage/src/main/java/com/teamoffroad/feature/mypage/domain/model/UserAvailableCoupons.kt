package com.teamoffroad.feature.mypage.domain.model

data class UserAvailableCoupons(
    val coupons: List<AvailableCoupons>,
    val availableCouponsCount: Int,
    val usedCouponsCount: Int
) {
    data class AvailableCoupons (
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val isNewGained: Boolean,
        val cursorId: Int
    )
}
