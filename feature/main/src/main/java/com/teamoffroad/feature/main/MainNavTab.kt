package com.teamoffroad.feature.main

import androidx.compose.runtime.Composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.offroad.feature.main.R

internal enum class MainNavTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_main_home,
        contentDescription = "홈",
        MainTabRoute.Home(),
    ),
    EXPLORE(
        iconResId = R.drawable.ic_main_explore,
        contentDescription = "탐험",
        MainTabRoute.Explore(),
    ),
    MYPAGE(
        iconResId = R.drawable.ic_main_mypage,
        contentDescription = "마이페이지",
        MainTabRoute.MyPage,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainNavTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}