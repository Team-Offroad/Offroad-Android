package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute
import com.teamoffroad.feature.mypage.presentation.AvailableCouponDetailScreen
import com.teamoffroad.feature.mypage.presentation.GainedCharacterScreen
import com.teamoffroad.feature.mypage.presentation.GainedCouponScreen
import com.teamoffroad.feature.mypage.presentation.MyPageScreen

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToGainedCharacter() {
    navigate(MyPageRoute.GainedCharacter)
}

fun NavController.navigateToGainedCoupon() {
    navigate(MyPageRoute.GainedCouponScreen)
}

fun NavController.navigateToAvailableCouponDetail(
    id: Int,
    name: String,
    couponImageUrl: String,
    description: String,
    placeId: Int,
) {
    navigate(MyPageRoute.AvailableCouponScreen(id, name, couponImageUrl, description, placeId))
}

fun NavGraphBuilder.myPageNavGraph(
    navigateToGainedCharacter: () -> Unit,
    navigateToGainedCoupon: () -> Unit,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.MyPage> {
        MyPageScreen(navigateToGainedCharacter, navigateToGainedCoupon)
    }

    composable<MyPageRoute.GainedCharacter> {
        GainedCharacterScreen(navigateToBack)
    }

    composable<MyPageRoute.GainedCouponScreen> {
        GainedCouponScreen(navigateToAvailableCouponDetail, navigateToBack)
    }

    composable<MyPageRoute.AvailableCouponScreen> { backStackEntry ->
        val id = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().id
        val name = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().name
        val couponImageUrl =
            backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().couponImageUrl
        val description = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().description
        val placeId = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().placeId
        AvailableCouponDetailScreen(id, name, couponImageUrl, description, placeId, navigateToBack)
    }
}
