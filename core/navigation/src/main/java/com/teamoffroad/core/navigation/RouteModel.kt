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
    data class Explore(val authResultState: String = "NONE", val imageUrl: String? = null) :
        MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}

sealed interface AuthRoute : Route {
    @Serializable
    data object SetNickname : AuthRoute

    @Serializable
    data object AgreeTermsAndConditions : AuthRoute

    @Serializable
    data class SetBirthDate(val nickname: String) : AuthRoute

    @Serializable
    data class SetGender(val nickname: String, val birthDate: String? = null) : AuthRoute

    @Serializable
    data object SetCharacter : AuthRoute

    @Serializable
    data class SelectedCharacter(val encodedUrl: String) : AuthRoute
}

sealed interface ExploreRoute : Route {
    @Serializable
    data class ExploreCameraScreen(val placeId: Long, val latitude: String, val longitude: String) :
        ExploreRoute

    @Serializable
    data object PlaceScreen : ExploreRoute

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
    ): MyPageRoute

    @Serializable
    data object GainedCharacter : MyPageRoute

    @Serializable
    data object GainedEmblems : MyPageRoute

    @Serializable
    data object Setting : MyPageRoute

    @Serializable
    data object Announcement : MyPageRoute

    @Serializable
    data class AnnouncementDetail(
        val title: String,
        val content: String,
        val link: String,
        val isImportant: Boolean,
    ) : MyPageRoute

    @Serializable
    data class CharacterDetail(
        val characterId: Int,
        val isRepresentative: Boolean,
    ) : MyPageRoute
}
