package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.mypage.MypageScreen

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavGraph() {
    composable<MainTabRoute.Mypage> {
        MypageScreen()
    }
}