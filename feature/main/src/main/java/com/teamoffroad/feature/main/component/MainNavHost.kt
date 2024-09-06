package com.teamoffroad.feature.main.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.feature.auth.navigation.authNavGraph
import com.teamoffroad.feature.explore.navigation.exploreNavGraph
import com.teamoffroad.feature.home.navigation.homeNavGraph
import com.teamoffroad.feature.main.MainNavigator
import com.teamoffroad.feature.mypage.navigation.myPageNavGraph

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Main1)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            homeNavGraph(
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
            )
            exploreNavGraph(
                navigateToHome = { category ->
                    navigator.popBackStack()
                    navigator.navigateToHome(category)
                },
                navigateToExplore = { errorType, successImageUrl ->
                    navigator.navigateToExplore(errorType, successImageUrl)
                },
                navigateToExploreCamera = { placeId, latitude, longitude ->
                    navigator.navigateToExploreCameraScreen(placeId, latitude, longitude)
                },
                navigateToPlace = {
                    navigator.navigateToPlace()
                },
                navigateToQuest = {
                    navigator.navigateToQuest()
                },
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
            )
            myPageNavGraph(
                navigateToGainedCoupon = {
                    navigator.navigateToGainedCoupon()
                },
                navigateToAvailableCouponDetail = { id, name, couponImageUrl, description, placeId ->
                    navigator.navigateToAvailableCouponDetail(id, name,couponImageUrl, description, placeId)
                },
                navigateToGainedCharacter = {
                    navigator.navigateToGainedCharacter()
                },
                navigateToGainedEmblems = navigator::navigateToGainedEmblems,
                navigateToSetting = navigator::navigateToSetting,
                navigateToAnnouncement = navigator::navigateToAnnouncement,
                navigateToAnnouncementDetail = navigator::navigateToAnnouncementDetail,
                navigateToSignIn = navigator::navigateToSignIn,
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
            )
            authNavGraph(
                navigateToHome = { navigator.navigateToHome() },
                navigateToAgreeTermsAndConditions = {},
                navigateToSetNickname = { navigator.navigateToSetNickname() },
                navigateToSetBirthDate = { nickname ->
                    navigator.navigateToSetBirthDate(nickname)
                },
                navigateToSetGender = { nickname, birthDate ->
                    navigator.navigateToSetGender(nickname, birthDate)
                },
                navigateToSetCharacter = {
                    navigator.navigateToSetCharacter()
                },
                navigateToSelectedCharacter = { selectedCharacterUrl ->
                    navigator.navigateToSelectedCharacter(selectedCharacterUrl)
                },
                navigateToBack = navigator::popBackStackIfNotMainTabRoute,
            )
        }
    }
}
