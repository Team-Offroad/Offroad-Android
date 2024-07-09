package com.teamoffroad.feature.explore.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
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
    padding: PaddingValues,
) {
    composable<MainTabRoute.Explore> {
        ExploreRoute(padding)
    }
}