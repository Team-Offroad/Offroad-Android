package com.teamoffroad.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.teamoffroad.feature.explore.navigation.navigateExplore
import com.teamoffroad.feature.home.navigation.navigateHome
import com.teamoffroad.feature.mypage.navigation.navigateMypage


internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = MainNavTab.HOME.route
    //Route.Auth
    //MainTab.HOME.route
    //요런식으로 시작점 바꿔서 작업하면 됩니다.

    val currentTab: MainNavTab?
        @Composable get() = MainNavTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainNavTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (tab) {
            MainNavTab.HOME -> navController.navigateHome(navOptions)
            MainNavTab.EXPLORE -> navController.navigateExplore(navOptions)
            MainNavTab.MYPAGE -> navController.navigateMypage(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}