package com.teamoffroad.feature.main

import androidx.compose.runtime.Composable
import com.teamoffroad.app.feature.main.R
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.Route

internal enum class MainTab(
    val iconResId: Int,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = R.drawable.ic_android_black_24dp,
        contentDescription = "홈",
        MainTabRoute.Home,
    ),
    EXPLORE(
        iconResId = R.drawable.ic_android_black_24dp,
        contentDescription = "탐험",
        MainTabRoute.Explore,
    ),
    MYPAGE(
        iconResId = R.drawable.ic_android_black_24dp,
        contentDescription = "마이페이지",
        MainTabRoute.Mypage,
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}