package com.teamoffroad.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthSettingRoute
import com.teamoffroad.feature.auth.presentation.SetGenderRoute

fun NavController.navigateToSetGender(navOptions: NavOptions) {
    navigate(AuthSettingRoute.SetGender, navOptions)
}

fun NavGraphBuilder.setGenderNavGraph(
    navigateToSetCharacter: () -> Unit,
) {
    composable<AuthSettingRoute.SetGender> {
        SetGenderRoute(
            navigateToSetCharacter,
        )
    }
}