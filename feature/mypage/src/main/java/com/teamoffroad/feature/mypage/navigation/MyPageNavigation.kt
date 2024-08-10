package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute
import com.teamoffroad.feature.mypage.presentation.MyPageScreen
import com.teamoffroad.feature.mypage.presentation.component.coupon.AcquireCouponScreen

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToAcquireCoupon(
    navOptions: NavOptions,
) {
    navigate(MyPageRoute.AcquireCoupon, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    navigateToMyPage: () -> Unit,
    navigateToAcquireCoupon: () -> Unit,
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.MyPage> {
        MyPageScreen(navigateToAcquireCoupon)
    }

    composable<MyPageRoute.AcquireCoupon> {
        AcquireCouponScreen(navigateToMyPage)
    }
}
