package com.teamoffroad.feature.mypage.presentation.component.coupon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.mypage.presentation.component.coupon.model.FakeGainedCouponModel

@Composable
fun GainedAvailableCouponItems(coupons: List<FakeGainedCouponModel.FakeAvailableCouponsModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 18.dp)
    ) {
        items(coupons.size) { index ->
            Text(text = coupons[index].name)
        }
    }
}

@Composable
fun GainedUsedCouponItems(coupons: List<FakeGainedCouponModel.FakeUsedCouponsModel>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(ListBg)
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(vertical = 18.dp)
    ) {
        items(coupons.size) { index ->
            Text(text = coupons[index].name)
        }
    }
}