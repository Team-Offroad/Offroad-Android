package com.teamoffroad.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthSettingRoute
import com.teamoffroad.feature.auth.presentation.SetCharacterRoute

fun NavController.navigateToSetCharacter(navOptions: NavOptions) {
    navigate(AuthSettingRoute.SetCharacter, navOptions)
}

fun NavGraphBuilder.setCharacterNavGraph(
    navigateToHome: () -> Unit,
) {
    composable<AuthSettingRoute.SetCharacter> {
        SetCharacterRoute(
            navigateToHome,
        )
    }
}