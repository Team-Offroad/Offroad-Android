package com.teamoffroad.feature.mypage.domain.model

data class UserCoupons(
    val coupons: List<Coupons>,
    val availableCouponsCount: Int,
    val usedCouponsCount: Int
) {
    data class Coupons (
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val isNewGained: Boolean,
        val cursorId: Int
    )
}
