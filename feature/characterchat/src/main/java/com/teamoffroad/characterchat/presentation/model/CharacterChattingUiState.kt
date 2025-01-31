package com.teamoffroad.characterchat.presentation.model

data class CharacterChattingUiState(
    val characterName: String = "",
    val characterChatContent: String = "",
    val isCharacterChattingExist: Boolean = false,
    val isCharacterChattingLoading: Boolean = false,
    val isUserWatchingCharacterChat: Boolean = false,
    val isAnswerButtonClicked: Boolean = false,
    val isError: Boolean = false
)
