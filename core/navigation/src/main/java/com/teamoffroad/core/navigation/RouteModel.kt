package com.teamoffroad.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Auth : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Explore : MainTabRoute

    @Serializable
    data object Mypage : MainTabRoute
}

sealed interface AuthSettingRoute : Route {
    @Serializable
    data object SetNickname : AuthSettingRoute

    @Serializable
    data object SetBirthDate : AuthSettingRoute

    @Serializable
    data object SetGender : AuthSettingRoute

    @Serializable
    data object SetCharacter : AuthSettingRoute
}

sealed interface ExploreRoute : Route {
    @Serializable
    data object ExploreCameraScreen : ExploreRoute
}