package com.teamoffroad.core.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Auth : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data class Home(val category: String? = null) : MainTabRoute

    @Serializable
    data class Explore(val authResultType: String? = null, val imageUrl: String? = null) : MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
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
    data class ExploreCameraScreen(val placeId: Long, val latitude: String, val longitude: String) : ExploreRoute
}
