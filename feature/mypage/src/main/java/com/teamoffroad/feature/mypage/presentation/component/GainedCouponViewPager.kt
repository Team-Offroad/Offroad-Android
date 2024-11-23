package com.teamoffroad.feature.mypage.presentation.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.teamoffroad.core.designsystem.theme.Gray300
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.designsystem.theme.Main2
import com.teamoffroad.core.designsystem.theme.OffroadTheme
import com.teamoffroad.core.designsystem.theme.Sub
import com.teamoffroad.feature.mypage.domain.model.UserCoupons
import com.teamoffroad.feature.mypage.presentation.model.UiState
import com.teamoffroad.offroad.feature.mypage.R
import kotlinx.coroutines.launch

@Composable
fun GainedCouponViewPager(
    availableCouponsCount: Int,
    usedCouponsCount: Int,
    availableCoupons: List<UserCoupons.Coupons>,
    usedCoupons: List<UserCoupons.Coupons>,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
    getUserCoupons: (Boolean, Int) -> Unit,
    uiState: UiState<UserCoupons>,
) {
    val tabTitles = listOf(
        stringResource(id = R.string.my_page_gained_coupon_available),
        stringResource(id = R.string.my_page_gained_coupon_used)
    )
    val pagerState = rememberPagerState(pageCount = { tabTitles.size })
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            containerColor = ListBg,
            contentColor = Main2,
            divider = {},
            indicator = { tabPositions ->
                val currentTabPosition = tabPositions[pagerState.currentPage]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(align = Alignment.BottomStart)
                        .offset(x = currentTabPosition.left)
                        .width(currentTabPosition.width)
                        .height(3.dp)
                        .background(Sub)
                )
            },
            modifier = Modifier.padding(horizontal = 24.dp)
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = index,
                                animationSpec = tween(durationMillis = 200)
                            )
                        }
                    },
                    text = {
                        Text(
                            text = if (index == 0) "$title $availableCouponsCount"
                            else "$title $usedCouponsCount",
                            style = OffroadTheme.typography.tooltipTitle
                        )
                    },
                    selectedContentColor = Main2,
                    unselectedContentColor = Gray300,
                    modifier = Modifier.background(Main1)
                )
            }
        }
        HorizontalDivider(color = Gray300)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            when (page) {
                0 -> AvailableCouponItems(
                    availableCouponsCount,
                    coupons = availableCoupons,
                    navigateToAvailableCouponDetail = navigateToAvailableCouponDetail,
                    getUserCoupons,
                )

                1 -> UsedCouponItems(
                    usedCouponsCount,
                    coupons = usedCoupons,
                    getUserCoupons,
                )
            }

        }
    }
}