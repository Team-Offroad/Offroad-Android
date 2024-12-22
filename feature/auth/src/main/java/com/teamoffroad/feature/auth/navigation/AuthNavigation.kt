package com.teamoffroad.feature.auth.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.core.navigation.AuthRoute
import com.teamoffroad.core.navigation.Route
import com.teamoffroad.feature.auth.presentation.signup.SelectedCharacterScreen
import com.teamoffroad.feature.auth.presentation.signup.SetCharacterScreen
import com.teamoffroad.feature.auth.presentation.signin.SignInScreen
import com.teamoffroad.feature.auth.presentation.signup.SignUpScreen
import com.teamoffroad.feature.auth.presentation.termandcondition.AgreeTermsAndConditionsScreen
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun NavController.navigateToAgreeTermsAndConditions() {
    navigate(AuthRoute.AgreeTermsAndConditions)
}

fun NavController.navigateToSignUp() {
    navigate(AuthRoute.SignUp)
}

fun NavController.navigateToSetCharacter(
    nickname: String,
    birthDate: String? = null,
    gender: String? = null,
) {
    navigate(AuthRoute.SetCharacter(nickname, birthDate, gender))
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
    navigateToSignUp: () -> Unit,
    navigateToSetCharacter: (String, String?, String?) -> Unit,
    navigateToSelectedCharacter: (String) -> Unit,
    navigateToBack: () -> Unit,
) {
    composable<Route.Auth> {
        SignInScreen(
            navigateToHome,
            navigateToAgreeTermsAndConditions,
        )
    }
    composable<AuthRoute.AgreeTermsAndConditions> {
        AgreeTermsAndConditionsScreen(
            navigateToSignUp
        )
    }
    composable<AuthRoute.SignUp> {
        SignUpScreen(
            navigateToSetCharacter = navigateToSetCharacter
        )
    }
    composable<AuthRoute.SetCharacter> { backStackEntry ->
        val nickname = backStackEntry.toRoute<AuthRoute.SetCharacter>().nickname
        val birthDate = backStackEntry.toRoute<AuthRoute.SetCharacter>().birthDate
        val gender = backStackEntry.toRoute<AuthRoute.SetCharacter>().gender
        SetCharacterScreen(
            nickname,
            birthDate,
            gender,
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
