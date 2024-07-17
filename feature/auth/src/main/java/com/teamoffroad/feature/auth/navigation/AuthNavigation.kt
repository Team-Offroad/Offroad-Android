package com.teamoffroad.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamoffroad.core.navigation.AuthRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.presentation.AuthScreen
import com.teamoffroad.feature.auth.presentation.SelectedCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetBirthDateScreen
import com.teamoffroad.feature.auth.presentation.SetCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetGenderScreen
import com.teamoffroad.feature.auth.presentation.SetNicknameScreen

fun NavController.navigateAuth(navOptions: NavOptions? = null) {
    navigate(Route.Auth, navOptions)
}

fun NavController.navigateToSetNickname(navOptions: NavOptions? = null) {
    navigate(AuthRoute.SetNickname, navOptions)
}

fun NavController.navigateToSetBirthDate(nickname: String, navOptions: NavOptions? = null) {
    val route = "${AuthRoute.SetBirthDate}/$nickname"
    navigate(route, navOptions)
}

fun NavController.navigateToSetGender(
    nickname: String,
    birthDate: String? = null,
    navOptions: NavOptions? = null
) {
    val route = if (birthDate != null) {
        "${AuthRoute.SetGender}/$nickname?birthDate=$birthDate"
    } else {
        "${AuthRoute.SetGender}/$nickname"
    }
    navigate(route, navOptions)
}

fun NavController.navigateToSetCharacter(navOptions: NavOptions) {
    navigate(AuthRoute.SetCharacter, navOptions)
}

fun NavGraphBuilder.authNavGraph(
    navigateToHome: () -> Unit,
    navigateToSetNickname: () -> Unit,
    navigateToSetBirthDate: (String) -> Unit,
    navigateToSetGender: (String, String?) -> Unit,
    navigateToSetCharacter: () -> Unit,
    navigateToSelectCharacter: () -> Unit,
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
    composable(
        route = "${AuthRoute.SetBirthDate}/{nickname}",
        arguments = listOf(
            navArgument("nickname") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val nickname = backStackEntry.arguments?.getString("nickname") ?: ""
        SetBirthDateScreen(
            nickname,
            navigateToSetGender,
        )
    }
    composable(
        route = "${AuthRoute.SetGender}/{nickname}?birthDate={birthDate}",
        arguments = listOf(
            navArgument("nickname") { type = NavType.StringType },
            navArgument("birthDate") { type = NavType.StringType; nullable = true }
        )
    ) { backStackEntry ->
        val nickname = backStackEntry.arguments?.getString("nickname") ?: ""
        val birthDate = backStackEntry.arguments?.getString("birthDate")
        SetGenderScreen(nickname, birthDate, navigateToSetCharacter)
    }
    composable<AuthRoute.SetCharacter> {
        SetCharacterScreen(
            navigateToSelectCharacter,
        )
    }
    composable<AuthRoute.SelectedCharacter> {
        SelectedCharacterScreen(
            navigateToHome
        )
    }
}
