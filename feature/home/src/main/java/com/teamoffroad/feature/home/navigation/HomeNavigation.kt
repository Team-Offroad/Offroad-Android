package com.teamoffroad.feature.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthRoute
import com.teamoffroad.core.navigation.HomeRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.home.presentation.HomeRoute

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    val options = navOptions ?: NavOptions.Builder()
        .setPopUpTo(graph.startDestinationId, inclusive = true)
        .build()
    navigate(MainTabRoute.Home, options)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
) {
    composable<MainTabRoute.Home> {
        HomeRoute(padding)
    }
}

fun NavController.navigateToHome(
    category: String,
    navOptions: NavOptions? = null
) {
    val route = "${HomeRoute.SetCategory}/$category"
    navigate(route, navOptions)
}