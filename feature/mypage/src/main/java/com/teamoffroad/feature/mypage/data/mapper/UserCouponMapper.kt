package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity.AvailableCouponsEntity
import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity.UsedCouponsEntity
import com.teamoffroad.feature.mypage.data.remote.request.UseCouponRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.UserCouponsResponseDto
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCouponList.AvailableCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCouponList.UsedCoupon

fun UserCouponsResponseDto.AvailableCouponsResponseDto.toData(): AvailableCouponsEntity {
    return AvailableCouponsEntity(
        id = id,
        name = name,
        couponImageUrl = couponImageUrl,
        description = description,
        isNewGained = isNewGained,
        placeId = placeId,
    )
}

fun UserCouponsResponseDto.UsedCouponsResponseDto.toData(): UsedCouponsEntity {
    return UsedCouponsEntity(
        name = name, couponImageUrl = couponImageUrl,
    )
}

fun AvailableCouponsEntity.toDomain(): AvailableCoupon {
    return AvailableCoupon(
        id = id,
        name = name,
        couponImageUrl = couponImageUrl,
        description = description,
        isNewGained = isNewGained,
        placeId = placeId,
    )
}

fun UsedCouponsEntity.toDomain(): UsedCoupon {
    return UsedCoupon(
        name = name, couponImageUrl = couponImageUrl,
    )
}

fun UseCoupon.toData(): UseCouponRequestDto {
    return UseCouponRequestDto(
        code = this.code,
        couponId = this.couponId,
        placeId = this.placeId,
    )
}