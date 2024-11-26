package com.teamoffroad.feature.mypage.data.model

data class UserUsedCouponsEntity(
    val coupons: List<UserCouponsEntity>,
    val availableCouponsCount: Int,
    val usedCouponsCount: Int
) {
    data class UserCouponsEntity(
        val name: String,
        val couponImageUrl: String,
        val cursorId: Int,
    )
}
