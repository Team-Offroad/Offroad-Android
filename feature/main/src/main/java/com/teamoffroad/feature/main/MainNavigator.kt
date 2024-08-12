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
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.navigation.navigateToSelectedCharacter
import com.teamoffroad.feature.auth.navigation.navigateToSetBirthDate
import com.teamoffroad.feature.auth.navigation.navigateToSetCharacter
import com.teamoffroad.feature.auth.navigation.navigateToSetGender
import com.teamoffroad.feature.auth.navigation.navigateToSetNickname
import com.teamoffroad.feature.explore.navigation.navigateToExplore
import com.teamoffroad.feature.explore.navigation.navigateToExploreCamera
import com.teamoffroad.feature.explore.navigation.navigateToPlace
import com.teamoffroad.feature.home.navigation.navigateToHome
import com.teamoffroad.feature.mypage.navigation.navigateToGainedCharacter
import com.teamoffroad.feature.mypage.navigation.navigateToMyPage

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

    private val navOptions by lazy {
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
            MainNavTab.HOME -> navController.navigateToHome(navOptions = navOptions)
            MainNavTab.EXPLORE -> navController.navigateToExplore(navOptions = navOptions)
            MainNavTab.MYPAGE -> navController.navigateToMyPage(navOptions = navOptions)
        }
    }

    fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotMain() {
        if (!isSameCurrentDestination<MainTabRoute.Home>() &&
            !isSameCurrentDestination<MainTabRoute.Explore>() &&
            !isSameCurrentDestination<MainTabRoute.MyPage>()
        ) popBackStack()
    }

    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun setBottomBarVisibility() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigateToHome(category: String? = null) {
        navController.navigateToHome(category, navOptions)
    }

    fun navigateToSetNickname() {
        navController.navigateToSetNickname(navOptions)
    }

    fun navigateToSetBirthDate(nickname: String) {
        navController.navigateToSetBirthDate(nickname, navOptions)
    }

    fun navigateToSetGender(nickname: String, birthDate: String?) {
        navController.navigateToSetGender(nickname, birthDate, navOptions)
    }

    fun navigateToSetCharacter() {
        navController.navigateToSetCharacter()
    }

    fun navigateToExploreCameraScreen(placeId: Long, latitude: Double, longitude: Double) {
        navController.navigateToExploreCamera(placeId, latitude, longitude, navOptions)
    }

    fun navigateToSelectedCharacter(selectedCharacterUrl: String) {
        navController.navigateToSelectedCharacter(selectedCharacterUrl, navOptions)
    }

    fun navigateToExplore(authResultType: String, imageUrl: String) {
        navController.navigateToExplore(authResultType, imageUrl, navOptions)
    }

    fun navigateToPlace() {
        navController.navigateToPlace(navOptions)
    }

    fun navigateToGainedCharacter() {
        navController.navigateToGainedCharacter(navOptions)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
