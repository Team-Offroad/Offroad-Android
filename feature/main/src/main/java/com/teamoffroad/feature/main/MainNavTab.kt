package com.teamoffroad.feature.main

import androidx.compose.runtime.Composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.offroad.feature.main.R

internal enum class MainNavTab(
    val selectedIconResId: Int,
    val deselectedIconResId: Int = 0,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        selectedIconResId = R.drawable.ic_main_home_selected,
        deselectedIconResId = R.drawable.ic_main_home_deselected,
        contentDescription = "홈",
        route = MainTabRoute.Home(),
    ),
    EXPLORE(
        selectedIconResId = R.drawable.ic_main_explore,
        contentDescription = "탐험",
        route = MainTabRoute.Explore(),
    ),
    MY_PAGE(
        selectedIconResId = R.drawable.ic_main_my_page_selected,
        deselectedIconResId = R.drawable.ic_main_my_page_deselected,
        contentDescription = "마이페이지",
        route = MainTabRoute.MyPage,
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