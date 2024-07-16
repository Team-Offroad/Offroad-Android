package com.teamoffroad.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthSettingRoute
import com.teamoffroad.feature.auth.presentation.SetBirthDateRoute

fun NavController.navigateToSetBirthDate(navOptions: NavOptions) {
    navigate(AuthSettingRoute.SetBirthDate, navOptions)
}

fun NavGraphBuilder.setBirthDateNavGraph(
    navigateToSetGender: () -> Unit,
) {
    composable<AuthSettingRoute.SetBirthDate> {
        SetBirthDateRoute(
            navigateToSetGender,
        )
    }
}