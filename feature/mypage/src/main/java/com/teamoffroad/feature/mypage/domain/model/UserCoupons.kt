package com.teamoffroad.feature.mypage.domain.model

data class UserCoupons(
    val id: Int,
    val name: String,
    val couponImageUrl: String,
    val description: String,
    val isNewGained: Boolean,
    val cursorId: Int
)
