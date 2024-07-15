package com.teamoffroad.feature.explore.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.ExploreRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.ExploreCameraScreen
import com.teamoffroad.feature.explore.presentation.ExploreScreen

fun NavController.navigateExplore(navOptions: NavOptions) {
    navigate(MainTabRoute.Explore, navOptions)
}

fun NavController.navigateToExploreCameraScreen(navOptions: NavOptions) {
    navigate(ExploreRoute.ExploreCameraScreen, navOptions)
}

fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: () -> Unit,
    navigateToExploreCameraScreen: () -> Unit,
) {
    composable<MainTabRoute.Explore> {
        ExploreScreen(navigateToHome, navigateToExploreCameraScreen)
    }
    composable<ExploreRoute.ExploreCameraScreen> {
        ExploreCameraScreen()
    }
}
