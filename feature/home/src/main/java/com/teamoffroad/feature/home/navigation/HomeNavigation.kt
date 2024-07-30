package com.teamoffroad.feature.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.home.presentation.HomeScreen

fun NavController.navigateToHome(category: String? = null, navOptions: NavOptions? = null) {
    val options = navOptions ?: NavOptions.Builder()
        .setPopUpTo(graph.startDestinationId, inclusive = true)
        .build()
    navigate(MainTabRoute.Home(category = category), navOptions = options)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    onBackClick: () -> Unit,
) {
    composable<MainTabRoute.Home> { backStackEntry ->
        val category = backStackEntry.toRoute<MainTabRoute.Home>().category
        HomeScreen(category = category)
    }
}
