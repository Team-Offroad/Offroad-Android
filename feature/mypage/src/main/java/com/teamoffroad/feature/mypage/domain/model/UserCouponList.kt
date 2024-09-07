package com.teamoffroad.feature.mypage.domain.model

data class UserCouponList(
    val availableCoupons: List<AvailableCoupon>,
    val usedCoupons: List<UsedCoupon>
) {
    data class AvailableCoupon(
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val isNewGained: Boolean,
        val placeId: Int
    )

    data class UsedCoupon(
        val name: String,
        val couponImageUrl: String,
    )
}
