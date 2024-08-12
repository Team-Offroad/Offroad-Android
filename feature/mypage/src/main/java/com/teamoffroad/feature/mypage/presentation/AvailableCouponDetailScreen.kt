package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AvailableCouponDetailScreen(
    id: Int,
    name: String,
    couponImageUrl: String,
    description: String,
    navigateToGainedCoupon: () -> Unit,
    availableCouponDetailViewModel: AvailableCouponDetailViewModel = hiltViewModel()
) {
    Column {
        Text(text = "$id")
        Text(text = name)
        Text(text = "$couponImageUrl")
        Text(text = "$description")
    }
}
