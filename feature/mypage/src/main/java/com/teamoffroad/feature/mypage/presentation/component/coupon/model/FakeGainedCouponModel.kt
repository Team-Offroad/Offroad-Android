package com.teamoffroad.feature.mypage.presentation.component.coupon.model

data class FakeGainedCouponModel(
    val availableCoupons: List<FakeAvailableCouponsModel>,
    val usedCoupons: List<FakeUsedCouponsModel>
) {
    data class FakeAvailableCouponsModel(
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String
    )

    data class FakeUsedCouponsModel(
        val name: String,
        val couponImageUrl: String
    )

    companion object {
        private val dummyGainedAvailableCoupon1 = FakeAvailableCouponsModel(
            id = 1,
            name = "쿠폰이름1",
            couponImageUrl = "https://github.com/user-attachments/assets/443755cf-5c0f-4c5d-959e-8eadb86bf6e8",
            description = "쿠폰 설명 블라블라1"
        )
        private val dummyGainedAvailableCoupon2 = FakeAvailableCouponsModel(
            id = 2,
            name = "쿠폰이름2",
            couponImageUrl = "https://github.com/user-attachments/assets/443755cf-5c0f-4c5d-959e-8eadb86bf6e8",
            description = "쿠폰 설명 블라블라2"
        )
        private val dummyGainedUsedCoupon1 = FakeUsedCouponsModel(
            name = "쿠폰이름3",
            couponImageUrl = "https://github.com/user-attachments/assets/443755cf-5c0f-4c5d-959e-8eadb86bf6e8"
        )
        private val dummyGainedUsedCoupon2 = FakeUsedCouponsModel(
            name = "쿠폰이름4",
            couponImageUrl = "https://github.com/user-attachments/assets/443755cf-5c0f-4c5d-959e-8eadb86bf6e8"
        )

        val dummyGainedCoupons = FakeGainedCouponModel(
            listOf(dummyGainedAvailableCoupon1, dummyGainedAvailableCoupon2),
            listOf(dummyGainedUsedCoupon1, dummyGainedUsedCoupon2)
        )
    }
}
