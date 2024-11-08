package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity
import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity.CouponsEntity
import com.teamoffroad.feature.mypage.data.remote.request.UseCouponRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.UserCouponsResponseDto
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons

fun UserCouponsResponseDto.CouponsResponseDto.toDomain(): UserCoupons {
    return UserCoupons(
        id = id,
        name = name,
        couponImageUrl = couponImageUrl,
        description = description,
        isNewGained = isNewGained,
        cursorId = cursorId
    )
}

fun UseCoupon.toData(): UseCouponRequestDto {
    return UseCouponRequestDto(
        code = this.code,
        couponId = this.couponId,
        placeId = this.placeId,
    )
}