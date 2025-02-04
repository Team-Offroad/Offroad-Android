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
import com.teamoffroad.feature.mypage.presentation.SupportScreen

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
    placeId: Int,
) {
    navigate(MyPageRoute.AvailableCouponScreen(id, name, couponImageUrl, description, placeId))
}

fun NavController.navigateToGainedEmblems() {
    navigate(MyPageRoute.GainedEmblems)
}

fun NavController.navigateToSetting() {
    navigate(MyPageRoute.Setting)
}

fun NavController.navigateToAnnouncement(announcementId: String?, navOptions: NavOptions? = null) {
    navigate(
        MyPageRoute.Announcement(announcementId), navOptions
    )
}

fun NavController.navigateToAnnouncementDetail(
    title: String,
    content: String,
    isImportant: Boolean,
    updateAt: String,
    hasExternalLinks: Boolean,
    externalLinks: List<String>,
    externalLinksTitles: List<String>,
) {
    navigate(
        MyPageRoute.AnnouncementDetail(
            title,
            content,
            isImportant,
            updateAt,
            hasExternalLinks,
            externalLinks,
            externalLinksTitles,
        )
    )
}

fun NavController.navigateToAuth() {
    navigate(Route.Auth)
}

fun NavController.navigateToCharacterDetail(characterId: Int, isRepresentative: Boolean) {
    navigate(MyPageRoute.CharacterDetail(characterId, isRepresentative))
}

fun NavController.navigateToSupport() {
    navigate(MyPageRoute.Support)
}

fun NavGraphBuilder.myPageNavGraph(
    navigateToGainedCharacter: () -> Unit,
    navigateToGainedCoupon: () -> Unit,
    navigateToAvailableCouponDetail: (Int, String, String, String, Int) -> Unit,
    navigateToGainedEmblems: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToAnnouncement: (String?) -> Unit,
    navigateToAnnouncementDetail: (String, String, Boolean, String, Boolean, List<String>, List<String>) -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToCharacterDetail: (Int, Boolean) -> Unit,
    navigateToBack: () -> Unit,
    navigateToCharacterChat: (Int, String) -> Unit,
    navigateToAnnouncementDeleteStack: () -> Unit,
    navigateToSupport: () -> Unit,
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
        GainedCouponScreen(navigateToAvailableCouponDetail, navigateToBack)
    }

    composable<MyPageRoute.AvailableCouponScreen> { backStackEntry ->
        val id = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().id
        val name = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().name
        val couponImageUrl =
            backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().couponImageUrl
        val description = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().description
        val placeId = backStackEntry.toRoute<MyPageRoute.AvailableCouponScreen>().placeId
        AvailableCouponDetailScreen(id, name, couponImageUrl, description, placeId, navigateToBack)
    }

    composable<MyPageRoute.GainedEmblems> {
        GainedEmblemsScreen(navigateToBack)
    }

    composable<MyPageRoute.Setting> {
        SettingScreen(
            navigateToAnnouncement = { navigateToAnnouncement(null) },
            navigateToSignIn = navigateToSignIn,
            navigateToSupport = navigateToSupport,
            navigateToBack = navigateToBack
        )
    }

    composable<MyPageRoute.Announcement> { backStackEntry ->
        val announcementId = backStackEntry.toRoute<MyPageRoute.Announcement>().announcementId
        AnnouncementScreen(
            announcementId,
            navigateToAnnouncementDetail,
            navigateToBack
        )
    }

    composable<MyPageRoute.AnnouncementDetail> { backStackEntry ->
        val title = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().title
        val content = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().content
        val isImportant = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().isImportant
        val updateAt = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().updateAt
        val hasExternalLinks =
            backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().hasExternalLinks
        val externalLinks = backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().externalLinks
        val externalLinksTitles: List<String> =
            backStackEntry.toRoute<MyPageRoute.AnnouncementDetail>().externalLinksTitles
        AnnouncementDetailScreen(
            title,
            content,
            isImportant,
            updateAt,
            hasExternalLinks,
            externalLinks,
            externalLinksTitles,
            navigateToAnnouncementDeleteStack,
        )
    }

    composable<MyPageRoute.Support> {
        SupportScreen(navigateToBack)
    }

    composable<MyPageRoute.CharacterDetail> { backStackEntry ->
        val characterId = backStackEntry.toRoute<MyPageRoute.CharacterDetail>().characterId
        val isRepresentative =
            backStackEntry.toRoute<MyPageRoute.CharacterDetail>().isRepresentative
        CharacterDetailScreen(characterId, isRepresentative, navigateToBack, navigateToCharacterChat)
    }
}
