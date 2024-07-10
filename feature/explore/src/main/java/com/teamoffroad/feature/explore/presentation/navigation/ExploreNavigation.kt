package com.teamoffroad.feature.explore.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.ExploreRoute

fun NavController.navigateExplore(navOptions: NavOptions) {
    navigate(MainTabRoute.Explore, navOptions)
}

fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: () -> Unit,
) {
    composable<MainTabRoute.Explore> {
        ExploreRoute(navigateToHome)
    }
}