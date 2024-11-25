package com.teamoffroad.feature.mypage.data.mapper

import com.teamoffroad.feature.mypage.data.model.UserAvailableCouponsEntity
import com.teamoffroad.feature.mypage.data.model.UserAvailableCouponsEntity.AvailableCouponsEntity
import com.teamoffroad.feature.mypage.data.model.UserUsedCouponsEntity
import com.teamoffroad.feature.mypage.data.remote.request.UseCouponRequestDto
import com.teamoffroad.feature.mypage.data.remote.response.UserAvailableCouponsResponseDto
import com.teamoffroad.feature.mypage.data.remote.response.UserUsedCouponsResponseDto
import com.teamoffroad.feature.mypage.domain.model.UseCoupon
import com.teamoffroad.feature.mypage.domain.model.UserAvailableCoupons
import com.teamoffroad.feature.mypage.domain.model.UserUsedCoupons

fun UserAvailableCouponsResponseDto.toData(): UserAvailableCouponsEntity {
    return UserAvailableCouponsEntity(
        coupons = this.coupons.map { coupon ->
            AvailableCouponsEntity(
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

fun UserAvailableCouponsEntity.toDomain(): UserAvailableCoupons {
    return UserAvailableCoupons(
        coupons = this.coupons.map { coupon ->
            UserAvailableCoupons.AvailableCoupons(
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

fun UserUsedCouponsResponseDto.toData(): UserUsedCouponsEntity {
    return UserUsedCouponsEntity(
        coupons = this.coupons.map { coupon ->
            UserUsedCouponsEntity.UserCouponsEntity(
                name = coupon.name,
                couponImageUrl = coupon.couponImageUrl,
                cursorId = coupon.cursorId
            )
        },
        availableCouponsCount = availableCouponsCount,
        usedCouponsCount = usedCouponsCount
    )
}

fun UserUsedCouponsEntity.toDomain(): UserUsedCoupons {
    return UserUsedCoupons(
        coupons = this.coupons.map { coupon ->
            UserUsedCoupons.UsedCoupons(
                name = coupon.name,
                couponImageUrl = coupon.couponImageUrl,
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