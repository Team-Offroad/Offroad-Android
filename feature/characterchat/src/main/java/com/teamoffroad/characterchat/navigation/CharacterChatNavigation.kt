package com.teamoffroad.characterchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.characterchat.presentation.CharacterChatScreen
import com.teamoffroad.core.navigation.CharacterChatRoute

fun NavController.navigateToCharacterChat(characterId: Int) {
    navigate(CharacterChatRoute.CharacterChat(characterId))
}

fun NavGraphBuilder.characterChatNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<CharacterChatRoute.CharacterChat> { backStackEntry ->
        val characterId = backStackEntry.toRoute<CharacterChatRoute.CharacterChat>().characterId
        CharacterChatScreen(
            characterId = characterId,
            navigateToBack = navigateToBack,
        )
    }
}
