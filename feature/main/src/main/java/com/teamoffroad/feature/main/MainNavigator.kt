package com.teamoffroad.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.navigation.navigateToAgreeTermsAndConditions
import com.teamoffroad.feature.auth.navigation.navigateToSelectedCharacter
import com.teamoffroad.feature.auth.navigation.navigateToSetBirthDate
import com.teamoffroad.feature.auth.navigation.navigateToSetCharacter
import com.teamoffroad.feature.auth.navigation.navigateToSetGender
import com.teamoffroad.feature.auth.navigation.navigateToSetNickname
import com.teamoffroad.feature.explore.navigation.navigateToExplore
import com.teamoffroad.feature.explore.navigation.navigateToExploreCamera
import com.teamoffroad.feature.explore.navigation.navigateToPlace
import com.teamoffroad.feature.explore.navigation.navigateToQuest
import com.teamoffroad.feature.home.navigation.navigateToHome
import com.teamoffroad.feature.mypage.navigation.navigateToAnnouncement
import com.teamoffroad.feature.mypage.navigation.navigateToAnnouncementDetail
import com.teamoffroad.feature.mypage.navigation.navigateToAvailableCouponDetail
import com.teamoffroad.feature.mypage.navigation.navigateToCharacterDetail
import com.teamoffroad.feature.mypage.navigation.navigateToGainedCharacter
import com.teamoffroad.feature.mypage.navigation.navigateToGainedCoupon
import com.teamoffroad.feature.mypage.navigation.navigateToGainedEmblems
import com.teamoffroad.feature.mypage.navigation.navigateToMyPage
import com.teamoffroad.feature.mypage.navigation.navigateToSetting
import com.teamoffroad.feature.mypage.navigation.navigateToSignIn

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

    private val mainTabNavOptions by lazy {
        navOptions {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigate(tab: MainNavTab) {
        when (tab) {
            MainNavTab.HOME -> navController.navigateToHome(navOptions = mainTabNavOptions)
            MainNavTab.EXPLORE -> navController.navigateToExplore(navOptions = mainTabNavOptions)
            MainNavTab.MYPAGE -> navController.navigateToMyPage(navOptions = mainTabNavOptions)
        }
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotMainTabRoute() {
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

    @Composable
    fun setBackButtonListenerEnabled() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    } || currentDestination?.hasRoute<Route.Auth>() == true

    fun navigateToSignIn() {
        navController.navigateToSignIn()
    }

    fun navigateToHome(category: String? = null, completeQuest: List<String> = emptyList()) {
        navController.navigateToHome(category, completeQuest, this.mainTabNavOptions)
    }

    fun navigateToAgreeTermsAndConditions() {
        navController.navigateToAgreeTermsAndConditions()
    }

    fun navigateToSetNickname() {
        navController.navigateToSetNickname()
    }

    fun navigateToSetBirthDate(nickname: String) {
        navController.navigateToSetBirthDate(nickname)
    }

    fun navigateToSetGender(nickname: String, birthDate: String?) {
        navController.navigateToSetGender(nickname, birthDate)
    }

    fun navigateToSetCharacter() {
        navController.navigateToSetCharacter()
    }

    fun navigateToExploreCameraScreen(placeId: Long, latitude: Double, longitude: Double) {
        navController.navigateToExploreCamera(placeId, latitude, longitude)
    }

    fun navigateToSelectedCharacter(selectedCharacterUrl: String) {
        navController.navigateToSelectedCharacter(selectedCharacterUrl)
    }

    fun navigateToMyPage() {
        navController.navigateToMyPage(mainTabNavOptions)
    }

    fun navigateToGainedCoupon() {
        navController.navigateToGainedCoupon()
    }

    fun navigateToAvailableCouponDetail(
        id: Int,
        name: String,
        couponImageUrl: String,
        description: String,
        placeId: Int,
    ) {
        navController.navigateToAvailableCouponDetail(
            id,
            name,
            couponImageUrl,
            description,
            placeId
        )
    }

    fun navigateToExplore(authResultType: String, imageUrl: String) {
        navController.navigateToExplore(authResultType, imageUrl, mainTabNavOptions)
    }

    fun navigateToPlace() {
        navController.navigateToPlace()
    }

    fun navigateToQuest() {
        navController.navigateToQuest()
    }

    fun navigateToGainedCharacter() {
        navController.navigateToGainedCharacter()
    }

    fun navigateToGainedEmblems() {
        navController.navigateToGainedEmblems()
    }

    fun navigateToSetting() {
        navController.navigateToSetting()
    }

    fun navigateToAnnouncement() {
        navController.navigateToAnnouncement()
    }

    fun navigateToAnnouncementDetail(
        title: String,
        content: String,
        isImportant: Boolean,
        updateAt: String,
    ) {
        navController.navigateToAnnouncementDetail(title, content, isImportant, updateAt)
    }

    fun navigateToCharacterDetail(characterId: Int, isRepresentative: Boolean) {
        navController.navigateToCharacterDetail(characterId, isRepresentative)
    }

    fun navigateToHomeFromExplore(category: String, completeQuest: List<String>) {
        val navOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
            launchSingleTop = true
        }
        navController.navigateToHome(category, completeQuest, navOptions)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
