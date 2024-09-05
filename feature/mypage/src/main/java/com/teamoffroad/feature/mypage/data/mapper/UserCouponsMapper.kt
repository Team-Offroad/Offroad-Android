package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity
import com.teamoffroad.feature.mypage.data.remote.request.UseCouponRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.UseCouponResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.UserCouponsResponseDto
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons

fun UserCouponsResponseDto.AvailableCouponsResponseDto.toData(): UserCouponsEntity.AvailableCouponsEntity {
    return UserCouponsEntity.AvailableCouponsEntity(
        id = id,
        name = name,
        couponImageUrl = couponImageUrl,
        description = description,
        isNewGained = isNewGained,
        placeId = placeId
    )
}

fun UserCouponsResponseDto.UsedCouponsResponseDto.toData(): UserCouponsEntity.UsedCouponsEntity {
    return UserCouponsEntity.UsedCouponsEntity(
        name = name, couponImageUrl = couponImageUrl, isNewGained = isNewGained
    )
}

fun UserCouponsEntity.AvailableCouponsEntity.toDomain(): UserCoupons.AvailableCoupon {
    return UserCoupons.AvailableCoupon(
        id = id,
        name = name,
        couponImageUrl = couponImageUrl,
        description = description,
        isNewGained = isNewGained,
        placeId = placeId
    )
}

fun UserCouponsEntity.UsedCouponsEntity.toDomain(): UserCoupons.UsedCoupon {
    return UserCoupons.UsedCoupon(
        name = name, couponImageUrl = couponImageUrl, isNewGained = isNewGained
    )
}

fun UseCoupon.toData(): UseCouponRequestDto {
    return UseCouponRequestDto(
        code = this.code,
        couponId = this.couponId,
        placeId = this.placeId
    )
}