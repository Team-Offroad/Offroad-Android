package com.teamoffroad.feature.mypage.domain.model

data class UserUsedCoupons(
    val coupons: List<UsedCoupons>,
    val availableCouponsCount: Int,
    val usedCouponsCount: Int
) {
    data class UsedCoupons (
        val name: String,
        val couponImageUrl: String,
        val cursorId: Int
    )
}
