package com.teamoffroad.feature.recommendplace.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.RecommendPlaceRoute
import com.teamoffroad.feature.recommendplace.presentation.RecommendPlaceScreen

fun NavController.navigateToRecommendPlace() {
    navigate(RecommendPlaceRoute.RecommendPlace)
}

fun NavGraphBuilder.recommendPlaceNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<RecommendPlaceRoute.RecommendPlace> { backStackEntry ->
        RecommendPlaceScreen(navigateToBack)
    }
}