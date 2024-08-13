package com.teamoffroad.feature.explore.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.ExploreRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.explore.presentation.ExploreCameraScreen
import com.teamoffroad.feature.explore.presentation.ExploreScreen
import com.teamoffroad.feature.explore.presentation.PlaceScreen
import com.teamoffroad.feature.explore.presentation.QuestScreen

fun NavController.navigateToExplore(
    authResultType: String? = null,
    imageUrl: String? = null,
    navOptions: NavOptions,
) {
    repeat(2) { popBackStack() }
    navigate(MainTabRoute.Explore(authResultType, imageUrl), navOptions)
}

fun NavController.navigateToExploreCamera(
    placeId: Long,
    latitude: Double,
    longitude: Double,
    navOptions: NavOptions,
) {
    navigate(ExploreRoute.ExploreCameraScreen(placeId, latitude.toString(), longitude.toString()), navOptions)
}

fun NavController.navigateToPlace(
    navOptions: NavOptions,
) {
    navigate(ExploreRoute.PlaceScreen, navOptions)
}

fun NavController.navigateToQuest(
    navOptions: NavOptions,
) {
    navigate(ExploreRoute.QuestScreen, navOptions)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.exploreNavGraph(
    navigateToHome: (String) -> Unit,
    navigateToExplore: (String, String) -> Unit,
    navigateToExploreCamera: (Long, Double, Double) -> Unit,
    navigateToPlace: () -> Unit,
    navigateToQuest: () -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.Explore> { backStackEntry ->
        val errorType = backStackEntry.toRoute<MainTabRoute.Explore>().authResultType
        val imageUrl = backStackEntry.toRoute<MainTabRoute.Explore>().imageUrl
        ExploreScreen(errorType, imageUrl, navigateToHome, navigateToExploreCamera, navigateToPlace, navigateToQuest)
    }

    composable<ExploreRoute.ExploreCameraScreen>(
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(400)
            )
        }
    ) { backStackEntry ->
        val placeId = backStackEntry.toRoute<ExploreRoute.ExploreCameraScreen>().placeId
        val latitude = backStackEntry.toRoute<ExploreRoute.ExploreCameraScreen>().latitude
        val longitude = backStackEntry.toRoute<ExploreRoute.ExploreCameraScreen>().longitude
        ExploreCameraScreen(placeId, latitude.toDouble(), longitude.toDouble(), navigateToExplore)
    }

    composable<ExploreRoute.PlaceScreen> {
        PlaceScreen(navigateToExplore)
    }

    composable<ExploreRoute.QuestScreen> {
        QuestScreen(navigateToExplore)
    }
}
