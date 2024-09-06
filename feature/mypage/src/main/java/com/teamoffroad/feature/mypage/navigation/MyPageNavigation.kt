package com.teamoffroad.feature.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.mypage.presentation.AnnouncementDetailScreen
import com.teamoffroad.feature.mypage.presentation.AnnouncementScreen
import com.teamoffroad.feature.mypage.presentation.AvailableCouponDetailScreen
import com.teamoffroad.feature.mypage.presentation.CharacterDetailScreen
import com.teamoffroad.feature.mypage.presentation.GainedCharacterScreen
import com.teamoffroad.feature.mypage.presentation.GainedCouponScreen
import com.teamoffroad.feature.mypage.presentation.GainedEmblemsScreen
import com.teamoffroad.feature.mypage.presentation.MyPageScreen
import com.teamoffroad.feature.mypage.presentation.SettingScreen

fun NavController.navigateToMyPage(navOptions: NavOptions) {
    navigate(MainTabRoute.MyPage, navOptions)
}

fun NavController.navigateToGainedCharacter() {
    navigate(MyPageRoute.GainedCharacter)
}

fun NavController.navigateToGainedCoupon() {
    navigate(MyPageRoute.GainedCouponScreen)
}

fun NavController.navigateToAvailableCouponDetail(
    id: Int,
    name: String,
    couponImageUrl: String,
    description: String,
) {
    navigate(MyPageRoute.AvailableCouponScreen(id, name, couponImageUrl, description))
}

fun NavController.navigateToGainedEmblems() {
    navigate(MyPageRoute.GainedEmblems)
}

fun NavController.navigateToSetting() {
    navigate(MyPageRoute.Setting)
}

fun NavController.navigateToAnnouncement() {
    navigate(MyPageRoute.Announcement)
}

fun NavController.navigateToAnnouncementDetail(
    title: String, content: String, link: String, isImportant: Boolean,
) {
    navigate(MyPageRoute.AnnouncementDetail(title, content, link, isImportant))
}

fun NavController.navigateToSignIn() {
    navigate(Route.Auth)
}

fun NavController.navigateToCharacterDetail(characterId: Int) {
    navigate(MyPageRoute.CharacterDetail(characterId))
}

fun NavGraphBuilder.myPageNavGraph(
    navigateToMyPage: () -> Unit,
    navigateToGainedCharacter: () -> Unit,
    navigateToGainedCoupon: () -> Unit,
    navigateToAvailableCouponDetail: (Int, String, String, String) -> Unit,
    navigateToGainedEmblems: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToAnnouncement: () -> Unit,
    navigateToAnnouncementDetail: (String, String, String, Boolean) -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToCharacterDetail: (Int) -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.MyPage> {
        MyPageScreen(
            navigateToGainedCharacter,
            navigateToGainedCoupon,
            navigateToGainedEmblems,
            navigateToSetting
        )
    }

    composable<MyPageRoute.GainedCharacter> {
        GainedCharacterScreen(navigateToCharacterDetail, navigateToBack)
    }

    composable<MyPageRoute.GainedCouponScreen> {
        GainedCouponScreen(navigateToAvailableCouponDetail, navigateToMyPage)
    }

    composable<MyPageRoute.AvailableCouponScreen> { backStackEntry ->
        val id = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().id
        val name = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().name
        val couponImageUrl =
            backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().couponImageUrl
        val description = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().description
        AvailableCouponDetailScreen(id, name, couponImageUrl, description, navigateToGainedCoupon)
    }

    composable<MyPageRoute.GainedEmblems> {
        GainedEmblemsScreen(navigateToBack)
    }

    composable<MyPageRoute.Setting> {
        SettingScreen(
            navigateToAnnouncement = navigateToAnnouncement,
            navigateToSignIn = navigateToSignIn,
            navigateToBack = navigateToBack
        )
    }

    composable<MyPageRoute.Announcement> {
        AnnouncementScreen(
            navigateToAnnouncementDetail,
            navigateToBack
        )
    }

    composable<MyPageRoute.AnnouncementDetail> { backStackEntry ->
        val title = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().title
        val content = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().content
        val link = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().link
        val isImportant = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().isImportant
        AnnouncementDetailScreen(title, content, link, isImportant, navigateToBack)
    }

    composable<MyPageRoute.CharacterDetail> { backStackEntry ->
        val characterId = backStackEntry.toRoute<MyPageRoute.CharacterDetail>().characterId
        CharacterDetailScreen(characterId, navigateToBack)
    }
}
