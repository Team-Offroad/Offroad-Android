package com.teamoffroad.feature.mypage.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.component.navigationPadding
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.mypage.presentation.GainedCouponViewModel.Companion.startCursorId
import com.teamoffroad.feature.mypage.presentation.component.GainedCouponViewPager
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun GainedCouponScreen(
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
    navigateToMyPage: () -> Unit,
    backgroundColor: Color = Main1,
    viewModel: GainedCouponViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        viewModel.getUserCoupons(true, startCursorId)
        viewModel.getUserCoupons(false, startCursorId)
    }

    Box(
        modifier = Modifier
            .navigationPadding()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            OffroadActionBar()
            NavigateBackAppBar(
                modifier = Modifier.padding(top = 20.dp),
                text = stringResource(id = R.string.my_page_my_page),
            ) { navigateToMyPage() }

            Row(
                modifier = Modifier.padding(start = 24.dp, top = 28.dp)
            ) {
                GainedCouponHeader()
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            GainedCouponViewPager(
                viewModel.userAvailableCoupons.collectAsState().value,
                viewModel.userUsedCoupons.collectAsState().value,
                navigateToAvailableCouponDetail
            )
        }
    }
}

@Composable
private fun GainedCouponHeader() {
    Text(
        text = stringResource(id = R.string.my_page_gained_coupon),
        style = OffroadTheme.typography.title,
        color = Main2,
    )
    Image(
        painter = painterResource(id = R.drawable.img_mypage_coupon),
        contentDescription = "coupon",
        modifier = Modifier
            .padding(start = 6.dp)
            .size(24.dp)
    )
}
