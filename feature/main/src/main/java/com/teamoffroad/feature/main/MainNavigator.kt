package com.teamoffroad.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.teamoffroad.characterchat.navigation.DEFAULT_CHARACTER_ID
import com.teamoffroad.characterchat.navigation.navigateToCharacterChat
import com.teamoffroad.core.navigation.CharacterChatRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.navigation.navigateToAgreeTermsAndConditions
import com.teamoffroad.feature.auth.navigation.navigateToSelectedCharacter
import com.teamoffroad.feature.auth.navigation.navigateToSetBirthDate
import com.teamoffroad.feature.auth.navigation.navigateToSetCharacter
import com.teamoffroad.feature.auth.navigation.navigateToSetGender
import com.teamoffroad.feature.auth.navigation.navigateToSetNickname
import com.teamoffroad.feature.explore.navigation.navigateToExplore
import com.teamoffroad.feature.explore.navigation.navigateToPlace
import com.teamoffroad.feature.explore.navigation.navigateToQuest
import com.teamoffroad.feature.home.navigation.navigateToHome
import com.teamoffroad.feature.mypage.navigation.navigateToAnnouncement
import com.teamoffroad.feature.mypage.navigation.navigateToAnnouncementDetail
import com.teamoffroad.feature.mypage.navigation.navigateToAuth
import com.teamoffroad.feature.mypage.navigation.navigateToAvailableCouponDetail
import com.teamoffroad.feature.mypage.navigation.navigateToCharacterDetail
import com.teamoffroad.feature.mypage.navigation.navigateToGainedCharacter
import com.teamoffroad.feature.mypage.navigation.navigateToGainedCoupon
import com.teamoffroad.feature.mypage.navigation.navigateToGainedEmblems
import com.teamoffroad.feature.mypage.navigation.navigateToMyPage
import com.teamoffroad.feature.mypage.navigation.navigateToSetting

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
            restoreState = false
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
    fun setBottomBarVisibility(): Boolean {
        val isMainNavTabRoute = MainNavTab.contains {
            currentDestination?.hasRoute(it::class) == true
        }
        val isCharacterChatRoute =
            currentDestination?.hasRoute<CharacterChatRoute.CharacterChat>() == true

        return isMainNavTabRoute || isCharacterChatRoute
    }


    @Composable
    fun setBackButtonListenerEnabled() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    } || currentDestination?.hasRoute<Route.Auth>() == true

    fun navigateToAuth() {
        navController.navigateToAuth()
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

    fun navigateToExplore(authResultType: String) {
        navController.navigateToExplore(authResultType, mainTabNavOptions)
    }

    fun navigateToPlace(latitude: String, longitude: String) {
        navController.navigateToPlace(latitude, longitude)
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

    fun navigateToAnnouncement(announcementId: String?) {
        navController.navigateToAnnouncement(announcementId)
    }

    fun navigateToAnnouncementDeleteStack() {
        navController.navigateToAnnouncement(null,
            navOptions {
                popUpTo<MyPageRoute.AnnouncementDetail> { inclusive = true }
                launchSingleTop = true
            }
        )
    }

    fun navigateToAnnouncementDetail(
        title: String,
        content: String,
        isImportant: Boolean,
        updateAt: String,
        hasExternalLinks: Boolean,
        externalLinks: List<String>,
        externalLinksTitles: List<String>,
    ) {
        navController.navigateToAnnouncementDetail(
            title,
            content,
            isImportant,
            updateAt,
            hasExternalLinks,
            externalLinks,
            externalLinksTitles
        )
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

    fun navigateToCharacterChat(characterId: Int = DEFAULT_CHARACTER_ID, characterName: String) {
        navController.navigateToCharacterChat(characterId, characterName)
    }
}

@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
