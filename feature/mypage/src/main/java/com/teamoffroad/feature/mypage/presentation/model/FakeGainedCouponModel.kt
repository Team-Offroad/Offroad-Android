package com.teamoffroad.feature.mypage.presentation.model

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
            couponImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMTFfMTg0%2FMDAxNzA3NjIzMTIyNjk1.KkOL7KS-CeF28QfSMz2rMl6JGtUK5CMNK-q67nrg2hsg.TV5f8Mwj8s-vrCWpoulmBVXHH8hz0WjET-TANWnWwnsg.JPEG.skskalcmfnrl%2F%25BB%25E7%25B0%25FA_%25284%2529.jpg&type=a340",
            description = "쿠폰 설명 블라블라1"
        )
        private val dummyGainedAvailableCoupon2 = FakeAvailableCouponsModel(
            id = 2,
            name = "쿠폰이름2",
            couponImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMTFfMTg0%2FMDAxNzA3NjIzMTIyNjk1.KkOL7KS-CeF28QfSMz2rMl6JGtUK5CMNK-q67nrg2hsg.TV5f8Mwj8s-vrCWpoulmBVXHH8hz0WjET-TANWnWwnsg.JPEG.skskalcmfnrl%2F%25BB%25E7%25B0%25FA_%25284%2529.jpg&type=a340",
            description = "쿠폰 설명 블라블라2"
        )
        private val dummyGainedUsedCoupon1 = FakeUsedCouponsModel(
            name = "쿠폰이름3",
            couponImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMTFfMTg0%2FMDAxNzA3NjIzMTIyNjk1.KkOL7KS-CeF28QfSMz2rMl6JGtUK5CMNK-q67nrg2hsg.TV5f8Mwj8s-vrCWpoulmBVXHH8hz0WjET-TANWnWwnsg.JPEG.skskalcmfnrl%2F%25BB%25E7%25B0%25FA_%25284%2529.jpg&type=a340"
        )
        private val dummyGainedUsedCoupon2 = FakeUsedCouponsModel(
            name = "쿠폰이름4",
            couponImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMTFfMTg0%2FMDAxNzA3NjIzMTIyNjk1.KkOL7KS-CeF28QfSMz2rMl6JGtUK5CMNK-q67nrg2hsg.TV5f8Mwj8s-vrCWpoulmBVXHH8hz0WjET-TANWnWwnsg.JPEG.skskalcmfnrl%2F%25BB%25E7%25B0%25FA_%25284%2529.jpg&type=a340"
        )

        val dummyGainedCoupons = FakeGainedCouponModel(
            listOf(dummyGainedAvailableCoupon1, dummyGainedAvailableCoupon2, dummyGainedAvailableCoupon1, dummyGainedAvailableCoupon2, dummyGainedAvailableCoupon1, dummyGainedAvailableCoupon2, dummyGainedAvailableCoupon1, dummyGainedAvailableCoupon2),
            listOf(dummyGainedUsedCoupon1, dummyGainedUsedCoupon2, dummyGainedUsedCoupon1, dummyGainedUsedCoupon2, dummyGainedUsedCoupon1, dummyGainedUsedCoupon2)
        )
    }
}
