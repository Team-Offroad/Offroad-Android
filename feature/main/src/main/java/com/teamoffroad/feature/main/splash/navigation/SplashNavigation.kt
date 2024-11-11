package com.teamoffroad.feature.main.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.main.splash.SplashScreen

fun NavController.navigateToAuth() {
    navigate(Route.Auth)
}

fun NavGraphBuilder.splashNavGraph(
    navigateToAuth: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<Route.Splash> {
        SplashScreen(
            navigateToAuth,
            navigateToHome,
        )
    }
}