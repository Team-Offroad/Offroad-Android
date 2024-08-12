package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
    id: Int,
    name: String,
    couponImageUrl: String,
    description: String,
    navOptions: NavOptions,
) {
    navigate(MyPageRoute.AvailableCouponScreen(id, name, couponImageUrl, description), navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    navigateToMyPage: () -> Unit,
    navigateToGainedCoupon: () -> Unit,
    navigateToAvailableCouponDetail: (Int, String, String, String) -> Unit,
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.MyPage> {
        MyPageScreen(navigateToGainedCoupon)
    }

    composable<MyPageRoute.GainedCouponScreen> {
        GainedCouponScreen(navigateToMyPage, navigateToAvailableCouponDetail)
    }

    composable<MyPageRoute.AvailableCouponScreen> { backStackEntry ->
        val id = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().id
        val name = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().name
        val couponImageUrl =
            backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().couponImageUrl
        val description = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().description
        AvailableCouponDetailScreen(id, name, couponImageUrl, description, navigateToGainedCoupon)
    }
}
