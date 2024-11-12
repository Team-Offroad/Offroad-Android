package com.teamoffroad.characterchat.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.teamoffroad.core.designsystem.theme.OffroadTheme

@Composable
fun CharacterChatScreen(
    navigateToBack: () -> Unit,
    characterChatViewModel: CharacterChatViewModel = hiltViewModel(),
) {
}

@Preview(showBackground = true)
@Composable
fun CharacterChatScreenPreview() {
    OffroadTheme {
        CharacterChatScreen(
            navigateToBack = {},
        )
    }
}
