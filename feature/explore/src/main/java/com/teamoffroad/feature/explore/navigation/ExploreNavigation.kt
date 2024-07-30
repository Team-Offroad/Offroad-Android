package com.teamoffroad.feature.explore.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.ExploreRoute
import com.teamoffroad.core.navigation.HomeRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.ExploreCameraScreen
import com.teamoffroad.feature.explore.presentation.ExploreScreen
import com.teamoffroad.feature.explore.presentation.model.ExploreCameraUiState
import com.teamoffroad.feature.home.presentation.HomeScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavController.navigateExplore(
    errorType: String,
    successImageUrl: String,
    navOptions: NavOptions,
) {
    val cameraNavOptions by lazy {
        popBackStack()
        navOptions {
            popUpTo(graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val encodedUrl = URLEncoder.encode(successImageUrl, StandardCharsets.UTF_8.toString())
    val route = "${MainTabRoute.Explore}/$errorType/$encodedUrl"
    navigate(route, cameraNavOptions)
}

fun NavController.navigateToExploreCameraScreen(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    navOptions: NavOptions,
) {
    navigate(ExploreRoute.ExploreCameraScreen(placeId, latitude.toString(), longitude.toString()), navOptions)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: (String) -> Unit,
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
        val errorType =
            backStackEntry.arguments?.getString("errorType") ?: ExploreCameraUiState.None.toString()
        val encodedUrl = backStackEntry.arguments?.getString("successImageUrl") ?: "None"
        val successImageUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
        ExploreScreen(errorType, successImageUrl, navigateToHome, navigateToExploreCameraScreen)
    }

    composable<ExploreRoute.ExploreCameraScreen> { backStackEntry ->
        val placeId = backStackEntry.toRoute<ExploreRoute.ExploreCameraScreen>().placeId
        val latitude = backStackEntry.toRoute<ExploreRoute.ExploreCameraScreen>().latitude
        val longitude = backStackEntry.toRoute<ExploreRoute.ExploreCameraScreen>().longitude
        ExploreCameraScreen(placeId, latitude.toDouble(), longitude.toDouble(), navigateToExplore)
    }

    composable(
        route = "${HomeRoute.SetCategory}/{category}",
        arguments = listOf(
            navArgument("category") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category") ?: "NONE"
        HomeScreen(category)
    }
}
