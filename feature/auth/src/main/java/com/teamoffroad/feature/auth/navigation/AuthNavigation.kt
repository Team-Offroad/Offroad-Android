package com.teamoffroad.feature.auth.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.designsystem.component.ChangeBottomBarColor
import com.teamoffroad.core.designsystem.theme.Main1
import com.teamoffroad.core.navigation.AuthRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.presentation.AgreeTermsAndConditionsScreen
import com.teamoffroad.feature.auth.presentation.AuthScreen
import com.teamoffroad.feature.auth.presentation.SelectedCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetBirthDateScreen
import com.teamoffroad.feature.auth.presentation.SetCharacterScreen
import com.teamoffroad.feature.auth.presentation.SetGenderScreen
import com.teamoffroad.feature.auth.presentation.SetNicknameScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavController.navigateToAgreeTermsAndConditions() {
    navigate(AuthRoute.AgreeTermsAndConditions)
}

fun NavController.navigateToSetNickname() {
    navigate(AuthRoute.SetNickname)
}

fun NavController.navigateToSetBirthDate(nickname: String) {
    navigate(AuthRoute.SetBirthDate(nickname))
}

fun NavController.navigateToSetGender(
    nickname: String,
    birthDate: String? = null,
) {
    navigate(AuthRoute.SetGender(nickname, birthDate))
}

fun NavController.navigateToSetCharacter() {
    navigate(AuthRoute.SetCharacter)
}

fun NavController.navigateToSelectedCharacter(
    selectedCharacterUrl: String,
) {
    val encodedUrl = URLEncoder.encode(selectedCharacterUrl, StandardCharsets.UTF_8.toString())
    navigate(AuthRoute.SelectedCharacter(encodedUrl))
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.authNavGraph(
    navigateToHome: () -> Unit,
    navigateToAgreeTermsAndConditions: () -> Unit,
    navigateToSetNickname: () -> Unit,
    navigateToSetBirthDate: (String) -> Unit,
    navigateToSetGender: (String, String?) -> Unit,
    navigateToSetCharacter: () -> Unit,
    navigateToSelectedCharacter: (String) -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<Route.Auth> {
        ChangeBottomBarColor(Main1)
        AuthScreen(
            navigateToHome,
            navigateToAgreeTermsAndConditions,
        )
    }
    composable<AuthRoute.AgreeTermsAndConditions> {
        AgreeTermsAndConditionsScreen(
            navigateToSetNickname
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
