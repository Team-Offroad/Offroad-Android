package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute
import com.teamoffroad.feature.mypage.presentation.AvailableCouponDetailScreen
import com.teamoffroad.feature.mypage.presentation.MyPageScreen
import com.teamoffroad.feature.mypage.presentation.GainedCouponScreen
import com.teamoffroad.feature.mypage.presentation.model.FakeGainedCouponModel

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToGainedCoupon(
    navOptions: NavOptions,
) {
    navigate(MyPageRoute.GainedCouponScreen, navOptions)
}

fun NavController.navigateToAvailableCouponDetail(
    navOptions: NavOptions,
) {
    navigate(MyPageRoute.AvailableCouponScreen, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    navigateToMyPage: () -> Unit,
    navigateToGainedCoupon: () -> Unit,
    navigateToAvailableCouponDetail: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.MyPage> {
        MyPageScreen(navigateToGainedCoupon)
    }

    composable<MyPageRoute.GainedCouponScreen> {
        GainedCouponScreen(navigateToMyPage, navigateToAvailableCouponDetail)
    }

    composable<MyPageRoute.AvailableCouponScreen> {
        AvailableCouponDetailScreen(navigateToGainedCoupon)
    }
}
