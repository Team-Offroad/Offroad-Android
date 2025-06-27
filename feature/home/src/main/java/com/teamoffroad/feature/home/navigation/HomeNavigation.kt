package com.teamoffroad.feature.home.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.feature.home.presentation.HomeScreen

fun NavController.navigateToHome(navOptions: NavOptions) {
    navigate(MainTabRoute.Home, navOptions = navOptions)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.homeNavGraph(
    navigateToBack: () -> Unit,
    navigateToGainedCharacter: () -> Unit,
    navigateToCharacterChatScreen: (String) -> Unit,
    navigateToDiary: (Boolean, String) -> Unit
) {
    composable<MainTabRoute.Home> {
        HomeScreen(
            navigateToGainedCharacter = navigateToGainedCharacter,
            navigateToCharacterChatScreen = navigateToCharacterChatScreen,
            navigateToDiary = navigateToDiary,
        )
    }
}
