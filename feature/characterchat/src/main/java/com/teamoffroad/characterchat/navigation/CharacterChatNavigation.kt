package com.teamoffroad.characterchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.teamoffroad.characterchat.presentation.CharacterChatScreen
import com.teamoffroad.core.navigation.MainTabRoute
import com.teamoffroad.core.navigation.MyPageRoute

fun NavController.navigateToCharacterChat() {
    navigate(MyPageRoute.CharacterChat)
}

fun NavGraphBuilder.characterChatNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<MainTabRoute.Home> {
        CharacterChatScreen(
            navigateToBack = navigateToBack,
        )
    }
}
