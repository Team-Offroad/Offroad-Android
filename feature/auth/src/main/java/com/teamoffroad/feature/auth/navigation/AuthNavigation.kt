package com.teamoffroad.feature.auth.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.AuthRoute

fun NavController.navigateAuth(navOptions: NavOptions) {
    navigate(Route.Auth, navOptions)
}

fun NavGraphBuilder.authNavGraph(
    padding: PaddingValues,
) {
    composable<Route.Auth> {
        AuthRoute(padding)
    }
}