package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute
import com.teamoffroad.feature.mypage.presentation.GainedCharacterScreen
import com.teamoffroad.feature.mypage.presentation.MyPageScreen

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToGainedCharacter() {
    navigate(MyPageRoute.GainedCharacter)
}

fun NavGraphBuilder.myPageNavGraph(
    navigateToGainedCharacter: (Int) -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.MyPage> {
        MyPageScreen(navigateToGainedCharacter)
    }
    composable<MyPageRoute.GainedCharacter> {
        GainedCharacterScreen(navigateToBack)
    }
}