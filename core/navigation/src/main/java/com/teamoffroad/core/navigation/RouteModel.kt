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

sealed interface AuthRoute : Route {
    @Serializable
    data object SetNickname : AuthRoute

    @Serializable
    data object SetBirthDate : AuthRoute

    @Serializable
    data object SetGender : AuthRoute

    @Serializable
    data object SetCharacter : AuthRoute

    @Serializable
    data object SelectedCharacter : AuthRoute
}

sealed interface ExploreRoute : Route {
    @Serializable
    data object ExploreCameraScreen : ExploreRoute
}

sealed interface HomeRoute: Route {
    @Serializable
    data object SetCategory: HomeRoute
}