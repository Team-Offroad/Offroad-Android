package com.teamoffroad.feature.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.teamoffroad.core.navigation.HomeRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.home.presentation.HomeScreen

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    val options by lazy {
        popBackStack()
        navOptions {
            popUpTo(graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    val route = MainTabRoute.Home
    navigate(route, options)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.Home> {
        HomeScreen("")
    }
}

fun NavController.navigateToHome(
    category: String,
    navOptions: NavOptions? = null,
) {
    val route = "${HomeRoute.SetCategory}/$category"
    navigate(route, navOptions)
}