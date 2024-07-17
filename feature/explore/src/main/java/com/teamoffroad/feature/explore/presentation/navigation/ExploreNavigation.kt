package com.teamoffroad.feature.explore.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamoffroad.core.navigation.ExploreRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.ExploreCameraScreen
import com.teamoffroad.feature.explore.presentation.ExploreScreen
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState

fun NavController.navigateExplore(navOptions: NavOptions) {
    navigate(MainTabRoute.Explore, navOptions)
}

fun NavController.navigateToExploreCameraScreen(placeId: Long, latitude: Double, longitude: Double, navOptions: NavOptions) {
    val route = "${ExploreRoute.ExploreCameraScreen}/$placeId/$latitude/$longitude"
    navigate(route, navOptions)
}

fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: () -> Unit,
    navigateToExplore: () -> Unit,
    navigateToExploreCameraScreen: (Long, Double, Double) -> Unit,
) {

    composable(
        route = "${MainTabRoute.Explore}/{errorType}",
        arguments = listOf(
            navArgument("errorType") { type = NavType.StringType },
        )
    ) {
        val errorType = it.arguments?.getString("errorType") ?: ExploreCameraUiState.Loading.toString()
        ExploreScreen(errorType, navigateToHome, navigateToExploreCameraScreen)
    }

    composable(
        route = "${ExploreRoute.ExploreCameraScreen}/{placeId}/{latitude}/{longitude}",
        arguments = listOf(
            navArgument("placeId") { type = NavType.LongType },
            navArgument("latitude") { type = NavType.StringType },
            navArgument("longitude") { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val placeId = backStackEntry.arguments?.getLong("placeId") ?: 0
        val latitude = backStackEntry.arguments?.getString("latitude")?.toDouble() ?: 0.0
        val longitude = backStackEntry.arguments?.getString("longitude")?.toDouble() ?: 0.0
        ExploreCameraScreen(placeId, latitude, longitude, navigateToExplore)
    }
}
