package com.teamoffroad.characterchat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.teamoffroad.characterchat.presentation.CharacterChatScreen
import com.teamoffroad.core.navigation.CharacterChatRoute

fun NavController.navigateToCharacterChat(characterId: Int, characterName: String) {
    navigate(CharacterChatRoute.CharacterChat(characterId, characterName))
}

fun NavGraphBuilder.characterChatNavGraph(
    navigateToBack: () -> Unit,
) {
    composable<CharacterChatRoute.CharacterChat> { backStackEntry ->
        val characterName = backStackEntry.toRoute<CharacterChatRoute.CharacterChat>().characterName
        val characterId = backStackEntry.toRoute<CharacterChatRoute.CharacterChat>().characterId

        CharacterChatScreen(
            characterId = when (characterId) {
                DEFAULT_CHARACTER_ID -> null
                else -> characterId
            },
            characterName = characterName,
            navigateToBack = navigateToBack,
        )
    }
}

const val DEFAULT_CHARACTER_ID = -1
