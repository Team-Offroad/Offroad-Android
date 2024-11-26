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

fun NavController.navigateToHome(
    category: String? = null,
    completeQuest: List<String> = emptyList(),
    navOptions: NavOptions,
) {
    navigate(MainTabRoute.Home(category = category, completeQuests = completeQuest), navOptions = navOptions)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    navigateToBack: () -> Unit,
    navigateToGainedCharacter: () -> Unit,
    navigateToCharacterChatScreen: (Int, String) -> Unit
) {
    composable<MainTabRoute.Home> { backStackEntry ->
        val category = backStackEntry.toRoute<MainTabRoute.Home>().category
        val completeQuests = backStackEntry.toRoute<MainTabRoute.Home>().completeQuests
        HomeScreen(
            category = category,
            completeQuests = completeQuests,
            navigateToGainedCharacter = navigateToGainedCharacter,
            navigateToCharacterChatScreen = navigateToCharacterChatScreen
        )
    }
}
