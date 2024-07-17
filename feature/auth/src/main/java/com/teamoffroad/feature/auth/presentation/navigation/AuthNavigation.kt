package com.teamoffroad.feature.auth.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.teamoffroad.core.navigation.AuthRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.presentation.AuthScreen
import com.teamoffroad.feature.auth.presentation.SetBirthDateScreen
import com.teamoffroad.feature.auth.presentation.SetCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetGenderScreen
import com.teamoffroad.feature.auth.presentation.SetNicknameScreen

fun NavController.navigateAuth(navOptions: NavOptions) {
    navigate(Route.Auth, navOptions)
}

fun NavController.navigateToSetBirthDate(navOptions: NavOptions) {
    navigate(AuthRoute.SetBirthDate, navOptions)
}

fun NavController.navigateToSetCharacter(navOptions: NavOptions) {
    navigate(AuthRoute.SetCharacter, navOptions)
}

fun NavController.navigateToSetGender(navOptions: NavOptions) {
    navigate(AuthRoute.SetGender, navOptions)
}

fun NavController.navigateToSetNickname(navOptions: NavOptions) {
    navigate(AuthRoute.SetNickname, navOptions)
}

fun NavGraphBuilder.authNavGraph(
    navigateToHome: () -> Unit,
    navigateToSetBirthDate: () -> Unit,
    navigateToSetGender: () -> Unit,
    navigateToSetNickname: () -> Unit,
    navigateToSetCharacter: () -> Unit,
) {
    composable<Route.Auth> {
        AuthScreen(
            navigateToHome,
            navigateToSetNickname,
        )
    }
    composable<AuthRoute.SetNickname> {
        SetNicknameScreen(
            navigateToSetBirthDate,
        )
    }
    composable<AuthRoute.SetBirthDate> {
        SetBirthDateScreen(
            navigateToSetGender,
        )
    }
    composable<AuthRoute.SetGender> {
        SetGenderScreen(
            navigateToSetCharacter,
        )
    }
    composable<AuthRoute.SetCharacter> {
        SetCharacterScreen(
            navigateToHome,
        )
    }
}
