package com.teamoffroad.feature.auth.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthSettingRoute
import com.teamoffroad.feature.auth.presentation.SetNicknameRoute

fun NavController.navigateToSetNickname(navOptions: NavOptions) {
    navigate(AuthSettingRoute.SetNickname, navOptions)
}

fun NavGraphBuilder.setNicknameNavGraph(
    padding: PaddingValues,
    navigateToSetBirthDate: () -> Unit,
) {
    composable<AuthSettingRoute.SetNickname> {
        SetNicknameRoute(
            padding,
            navigateToSetBirthDate
        )
    }
}