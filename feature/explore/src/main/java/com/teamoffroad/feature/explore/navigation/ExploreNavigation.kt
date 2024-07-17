package com.teamoffroad.feature.explore.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.teamoffroad.core.navigation.ExploreRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.ExploreCameraScreen
import com.teamoffroad.feature.explore.presentation.ExploreScreen
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState

fun NavController.navigateExplore(errorType: String, successImageUrl: String, navOptions: NavOptions) {
    val cameraNavOptions by lazy {
        navOptions {
            popUpTo(graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val route = "${MainTabRoute.Explore}/$errorType/$successImageUrl"
    navigate(route, cameraNavOptions)
}

fun NavController.navigateToExploreCameraScreen(placeId: Long, latitude: Double, longitude: Double, navOptions: NavOptions) {
    val route = "${ExploreRoute.ExploreCameraScreen}/$placeId/$latitude/$longitude"
    navigate(route, navOptions)
}

fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: () -> Unit,
    navigateToExplore: (String, String) -> Unit,
    navigateToExploreCameraScreen: (Long, Double, Double) -> Unit,
) {

    composable(
        route = "${MainTabRoute.Explore}/{errorType}/{successImageUrl}",
        arguments = listOf(
            navArgument("errorType") { type = NavType.StringType },
            navArgument("successImageUrl") { type = NavType.StringType },
        )
    ) { backStackEntry ->
        val errorType = backStackEntry.arguments?.getString("errorType") ?: ExploreCameraUiState.None.toString()
        val successImageUrl = backStackEntry.arguments?.getString("successImageUrl") ?: "None"
        ExploreScreen(errorType, successImageUrl, navigateToHome, navigateToExploreCameraScreen)
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
