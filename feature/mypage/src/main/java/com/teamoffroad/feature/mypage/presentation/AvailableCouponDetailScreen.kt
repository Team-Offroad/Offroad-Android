package com.teamoffroad.feature.mypage.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun AvailableCouponDetailScreen(
    navigateToGainedCoupon: () -> Unit,
    availableCouponDetailViewModel: AvailableCouponDetailViewModel = hiltViewModel()
) {
    Text(text = "available coupon detail screen")
}


@Preview(showBackground = true)
@Composable
fun AvailableCouponDetailScreenPreview() {
    OffroadTheme {
        AvailableCouponDetailScreen(navigateToGainedCoupon = { })
    }
}
