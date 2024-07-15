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
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.presentation.navigation.navigateToSetBirthDate
import com.teamoffroad.feature.auth.presentation.navigation.navigateToSetGender
import com.teamoffroad.feature.auth.presentation.navigation.navigateToSetNickname
import com.teamoffroad.feature.explore.presentation.navigation.navigateExplore
import com.teamoffroad.feature.explore.presentation.navigation.navigateToExploreCameraScreen
import com.teamoffroad.feature.home.navigation.navigateToHome
import com.teamoffroad.feature.mypage.navigation.navigateMypage

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Route.Auth

    val currentTab: MainNavTab?
        @Composable get() = MainNavTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    val navOptions by lazy {
        navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigate(tab: MainNavTab) {
        when (tab) {
            MainNavTab.HOME -> navController.navigateToHome(navOptions)
            MainNavTab.EXPLORE -> navController.navigateExplore(navOptions)
            MainNavTab.MYPAGE -> navController.navigateMypage(navOptions)
        }
    }

    @Composable
    fun setBottomBarVisibility() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateToHome() {
        navController.navigateToHome(navOptions)
    }

    fun navigateToSetNickname() {
        navController.navigateToSetNickname(navOptions)
    }

    fun navigateToSetBirthDate() {
        navController.navigateToSetBirthDate(navOptions)
    }

    fun navigateToSetGender() {
        navController.navigateToSetGender(navOptions)
    }

    fun navigateToExploreCameraScreen(placeId: Long, latitude: Double, longitude: Double) {
        navController.navigateToExploreCameraScreen(placeId, latitude, longitude, navOptions)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}