package com.teamoffroad.characterchat.presentation.model

import java.time.LocalDate

data class CharacterChatUiState(
    val chats: Map<LocalDate, List<ChatModel>> = emptyMap(),
    val characterId: Int = -1,
    val characterName: String = "",
    val isLoading: Boolean = true,
    val isSending: Boolean = false,
    val isError: Boolean = false,
)