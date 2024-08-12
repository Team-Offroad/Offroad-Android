package com.teamoffroad.feature.mypage.presentation

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.component.NavigateBackAppBar
import com.teamoffroad.core.designsystem.component.OffroadActionBar
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub4
import com.teamoffroad.feature.mypage.presentation.component.GainedCouponViewPager
import com.teamoffroad.feature.mypage.presentation.model.FakeGainedCouponModel
import com.teamoffroad.offroad.feature.mypage.R

@Composable
internal fun GainedCouponScreen(
    navigateToMyPage: () -> Unit,
    navigateToAvailableCouponDetail: (Int, String, String, String) -> Unit,
    gainedCouponViewModel: GainedCouponViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .background(Sub4)
            .then(
                if (!checkNavigationBar(LocalView.current)) Modifier.navigationBarsPadding() else Modifier.padding(
                    bottom = 14.dp
                )
            )
    ) {
        Column(
            modifier = Modifier
                .background(ListBg)
                .fillMaxSize(),
        ) {
            OffroadActionBar()
            NavigateBackAppBar(
                modifier = Modifier
                    .background(ListBg)
                    .padding(top = 20.dp),
                text = stringResource(id = R.string.mypage_mypage),
                backgroundColor = ListBg
            ) { navigateToMyPage() }

            Row(
                modifier = Modifier.padding(start = 24.dp, top = 28.dp)
            ) {
                GainedCouponHeader()
            }
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
            GainedCouponViewPager(FakeGainedCouponModel.dummyGainedCoupons, navigateToAvailableCouponDetail)
        }
    }
}

@Composable
private fun GainedCouponHeader() {
    Text(
        text = stringResource(id = R.string.gained_coupon),
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

@Composable
fun checkNavigationBar(view: View): Boolean = remember {
    val systemBottomNavigationSetting = view.context.resources.getIdentifier(
        "config_navBarInteractionMode",
        "integer",
        "android"
    )
    systemBottomNavigationSetting > UNAVAILABLE_RESOURCE_ID && view.context.resources.getInteger(
        systemBottomNavigationSetting
    ) == GESTURE_NAVIGATION
}

private const val GESTURE_NAVIGATION = 2
private const val UNAVAILABLE_RESOURCE_ID = 0