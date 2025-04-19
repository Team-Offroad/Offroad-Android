package com.teamoffroad.feature.main.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthRoute

fun NavController.navigateToSplash() {
    navigate(AuthRoute.Splash)
}

fun NavGraphBuilder.splashNavGraph(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit,
) {
    composable<AuthRoute.Splash> {
        SplashScreen(
            navigateToHome = navigateToHome,
            navigateToSignIn = navigateToSignIn
        )
    }
}