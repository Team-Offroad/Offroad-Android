package com.teamoffroad.characterchat.presentation.model

import java.time.LocalDate

data class CharacterChatUiState(
    val chats: Map<LocalDate, List<ChatModel>> = emptyMap(),
    val characterName: String = "",
    val isLoading: Boolean = true,
    val isError: Boolean = false,
)