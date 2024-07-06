package com.teamoffroad.feature.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.mypage.MypageRoute

fun NavController.navigateMypage(navOptions: NavOptions) {
    navigate(MainTabRoute.Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.Mypage> {
        MypageRoute(padding)
    }
}