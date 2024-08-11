package com.teamoffroad.feature.auth.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.AuthRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.presentation.AuthScreen
import com.teamoffroad.feature.auth.presentation.SelectedCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetBirthDateScreen
import com.teamoffroad.feature.auth.presentation.SetCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetGenderScreen
import com.teamoffroad.feature.auth.presentation.SetNicknameScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavController.navigateAuth(navOptions: NavOptions? = null) {
    navigate(Route.Auth, navOptions)
}

fun NavController.navigateToSetNickname(navOptions: NavOptions? = null) {
    navigate(AuthRoute.SetNickname, navOptions)
}

fun NavController.navigateToSetBirthDate(nickname: String, navOptions: NavOptions? = null) {
    navigate(AuthRoute.SetBirthDate(nickname), navOptions)
}

fun NavController.navigateToSetGender(
    nickname: String,
    birthDate: String? = null,
    navOptions: NavOptions? = null,
) {
    navigate(AuthRoute.SetGender(nickname, birthDate), navOptions)
}

fun NavController.navigateToSetCharacter(navOptions: NavOptions? = null) {
    navigate(AuthRoute.SetCharacter, navOptions)
}

fun NavController.navigateToSelectedCharacter(
    selectedCharacterUrl: String,
    navOptions: NavOptions,
) {
    val encodedUrl = URLEncoder.encode(selectedCharacterUrl, StandardCharsets.UTF_8.toString())
    navigate(AuthRoute.SelectedCharacter(encodedUrl), navOptions)
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.authNavGraph(
    navigateToHome: () -> Unit,
    navigateToSetNickname: () -> Unit,
    navigateToSetBirthDate: (String) -> Unit,
    navigateToSetGender: (String, String?) -> Unit,
    navigateToSetCharacter: () -> Unit,
    navigateToSelectedCharacter: (String) -> Unit,
    onBackClick: () -> Unit,
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
    composable<AuthRoute.SetBirthDate> { backStackEntry ->
        val nickname = backStackEntry.toRoute<AuthRoute.SetBirthDate>().nickname
        SetBirthDateScreen(
            nickname,
            navigateToSetGender,
        )
    }
    composable<AuthRoute.SetGender> { backStackEntry ->
        val nickname = backStackEntry.toRoute<AuthRoute.SetGender>().nickname
        val birthDate = backStackEntry.toRoute<AuthRoute.SetGender>().birthDate
        SetGenderScreen(nickname, birthDate, navigateToSetCharacter)
    }
    composable<AuthRoute.SetCharacter> {
        SetCharacterScreen(
            navigateToSelectedCharacter,
        )
    }
    composable<AuthRoute.SelectedCharacter> { backStackEntry ->
        val encodedUrl = backStackEntry.toRoute<AuthRoute.SelectedCharacter>().encodedUrl
        val selectedCharacterUrl = URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.toString())
        SelectedCharacterScreen(
            selectedCharacterUrl,
            navigateToHome,
        )
    }
}
