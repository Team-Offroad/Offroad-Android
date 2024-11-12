package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity
import com.teamoffroad.feature.mypage.data.model.UserCouponsEntity.CouponsEntity
import com.teamoffroad.feature.mypage.data.remote.request.UseCouponRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.UserCouponsResponseDto
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserCoupons

fun UserCouponsResponseDto.toData(): UserCouponsEntity {
    return UserCouponsEntity(
        coupons = this.coupons.map { coupon ->
            CouponsEntity(
                id = coupon.id,
                name = coupon.name,
                couponImageUrl = coupon.couponImageUrl,
                description = coupon.description,
                isNewGained = coupon.isNewGained,
                cursorId = coupon.cursorId
            )
        },
        availableCouponsCount = availableCouponsCount,
        usedCouponsCount = usedCouponsCount
    )
}

fun UserCouponsEntity.toDomain(): UserCoupons {
    return UserCoupons(
        coupons = this.coupons.map { coupon ->
            UserCoupons.Coupons(
                id = coupon.id,
                name = coupon.name,
                couponImageUrl = coupon.couponImageUrl,
                description = coupon.description,
                isNewGained = coupon.isNewGained,
                cursorId = coupon.cursorId
            )
        },
        availableCouponsCount = availableCouponsCount,
        usedCouponsCount = usedCouponsCount
    )
}

fun UseCoupon.toData(): UseCouponRequestDto {
    return UseCouponRequestDto(
        code = this.code,
        couponId = this.couponId,
        placeId = this.placeId,
    )
}