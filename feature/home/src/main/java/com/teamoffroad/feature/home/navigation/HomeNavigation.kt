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

fun NavController.navigateToHome(category: String? = null, navOptions: NavOptions) {
    navigate(MainTabRoute.Home(category = category), navOptions = navOptions)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.Home> { backStackEntry ->
        val category = backStackEntry.toRoute<MainTabRoute.Home>().category
        HomeScreen(category = category)
    }
}
