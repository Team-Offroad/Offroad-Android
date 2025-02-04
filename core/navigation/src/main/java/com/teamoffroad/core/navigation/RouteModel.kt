package com.teamoffroad.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Auth : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data class Home(
        val category: String? = null,
        val completeQuests: List<String> = emptyList(),
    ) : MainTabRoute

    @Serializable
    data class Explore(val authResultState: String = "NONE") :
        MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}

sealed interface AuthRoute : Route {

    @Serializable
    data object AgreeTermsAndConditions : AuthRoute

    @Serializable
    data object SignUp : AuthRoute

    @Serializable
    data class SetCharacter(
        val nickname: String,
        val birthDate: String? = null,
        val gender: String?,
    ) : AuthRoute

    @Serializable
    data class SelectedCharacter(val encodedUrl: String) : AuthRoute
}

sealed interface ExploreRoute : Route {
    @Serializable
    data class PlaceScreen(
        val latitude: String,
        val longitude: String,
    ) : ExploreRoute

    @Serializable
    data object QuestScreen : ExploreRoute
}

sealed interface MyPageRoute : Route {
    @Serializable
    data object GainedCouponScreen : MyPageRoute

    @Serializable
    data class AvailableCouponScreen(
        val id: Int,
        val name: String,
        val couponImageUrl: String,
        val description: String,
        val placeId: Int,
    ) : MyPageRoute

    @Serializable
    data object GainedCharacter : MyPageRoute

    @Serializable
    data object GainedEmblems : MyPageRoute

    @Serializable
    data object Setting : MyPageRoute

    @Serializable
    data class Announcement(
        val announcementId: String?,
    ) : MyPageRoute

    @Serializable
    data class AnnouncementDetail(
        val title: String,
        val content: String,
        val isImportant: Boolean,
        val updateAt: String,
        val hasExternalLinks: Boolean,
        val externalLinks: List<String>,
        val externalLinksTitles: List<String>,
    ) : MyPageRoute

    @Serializable
    data class CharacterDetail(
        val characterId: Int,
        val isRepresentative: Boolean,
    ) : MyPageRoute

    @Serializable
    data object Support : MyPageRoute
}

sealed interface CharacterChatRoute : Route {
    @Serializable
    data class CharacterChat(
        val characterId: Int,
        val characterName: String,
    ) : CharacterChatRoute
}
