package com.teamoffroad.feature.mypage.data.model

data class UserCouponsEntity(
    val coupons: List<CouponsEntity>,
) {
    data class CouponsEntity(
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val isNewGained: Boolean,
        val cursorId: Int,
    )
}
