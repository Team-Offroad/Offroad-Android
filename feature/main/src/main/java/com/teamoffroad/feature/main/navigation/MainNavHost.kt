package com.teamoffroad.feature.main.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.teamoffroad.characterchat.navigation.characterChatNavGraph
import com.teamoffroad.core.designsystem.theme.ListBg
import com.teamoffroad.feature.auth.navigation.authNavGraph
import com.teamoffroad.feature.diary.navigation.diaryNavGraph
import com.teamoffroad.feature.explore.navigation.exploreNavGraph
import com.teamoffroad.feature.home.navigation.homeNavGraph
import com.teamoffroad.feature.main.splash.splashNavGraph
import com.teamoffroad.feature.mypage.navigation.myPageNavGraph
import com.teamoffroad.feature.recommendplace.navigation.recommendPlaceNavGraph

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(ListBg),
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            enterTransition = { EnterTransition.None },
            popEnterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },
            popExitTransition = { ExitTransition.None },
        ) {
            splashNavGraph(
                navigateToHome = navigator::navigateToHome,
                navigateToSignIn = navigator::navigateToAuth,
            )
            homeNavGraph(
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
                navigateToCharacterChatScreen = { characterName ->
                    navigator.navigateToCharacterChat(characterName)
                },
                navigateToGainedCharacter = { beforeNavigateRoute ->
                    navigator.navigateToGainedCharacter(beforeNavigateRoute = beforeNavigateRoute)
                },
                navigateToDiary = navigator::navigateToDiary,
                navigateToRecommendPlace = navigator::navigateToRecommendPlace,
            )
            exploreNavGraph(
                navigateToHome = {
                    navigator.navigateToHome()
                },
                navigateToPlace = { latitude, longitude ->
                    navigator.navigateToPlace(latitude, longitude)
                },
                navigateToQuest = {
                    navigator.navigateToQuest()
                },
                navigateToQuestDetail = { questId, deadline, dDay ->
                    navigator.navigateToCourseQuestDetail(questId, deadline, dDay)
                },
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
            )
            myPageNavGraph(
                navigateToGainedCoupon = {
                    navigator.navigateToGainedCoupon()
                },
                navigateToAvailableCouponDetail = { id, name, couponImageUrl, description, placeId ->
                    navigator.navigateToAvailableCouponDetail(
                        id,
                        name,
                        couponImageUrl,
                        description,
                        placeId,
                    )
                },
                navigateToGainedCharacter = { beforeNavigateRoute ->
                    navigator.navigateToGainedCharacter(beforeNavigateRoute)
                },
                navigateToGainedEmblems = navigator::navigateToGainedEmblems,
                navigateToSetting = navigator::navigateToSetting,
                navigateToAnnouncement = { announcementId ->
                    navigator.navigateToAnnouncement(announcementId)
                },
                navigateToAnnouncementDetail = navigator::navigateToAnnouncementDetail,
                navigateToSignIn = navigator::navigateToAuth,
                navigateToCharacterDetail = navigator::navigateToCharacterDetail,
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
                navigateToCharacterChat = navigator::navigateToCharacterChat,
                navigateToAnnouncementDeleteStack = navigator::navigateToAnnouncementDeleteStack,
                navigateToSupport = navigator::navigateToSupport,
                navigateToDiary = navigator::navigateToDiary,
                navigateToDiaryTime = navigator::navigateToDiaryTime,
            )
            authNavGraph(
                navigateToHome = { navigator.navigateToHome() },
                navigateToAgreeTermsAndConditions = { navigator.navigateToAgreeTermsAndConditions() },
                navigateToSignUp = { navigator.navigateToSignUp() },
                navigateToSetCharacter = { nickname, birthDate, gender ->
                    navigator.navigateToSetCharacter(nickname, birthDate, gender)
                },
                navigateToSelectedCharacter = { selectedCharacterUrl ->
                    navigator.navigateToSelectedCharacter(selectedCharacterUrl)
                },
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
            )
            characterChatNavGraph(
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
                navigateToRecommendPlace = navigator::navigateToRecommendPlace
            )
            diaryNavGraph(
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
                navigateToCharacterChat = navigator::navigateToCharacterChat,
                navigateToDiaryTime = navigator::navigateToDiaryTime,
            )
            recommendPlaceNavGraph(
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
                navigateToOrderRecommendPlace = navigator::navigateToOrderRecommendPlace
            )
        }
    }
}
