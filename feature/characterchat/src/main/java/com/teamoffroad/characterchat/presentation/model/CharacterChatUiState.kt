package com.teamoffroad.characterchat.presentation.model

import java.time.LocalDate

data class CharacterChatUiState(
    val chats: Map<LocalDate, List<ChatModel>> = emptyMap(),
    val characterId: Int? = null,
    val characterName: String = "",
    val isLoading: Boolean = false,
    val isSending: Boolean = false,
    val isError: Boolean = false,
    val isLoadable: Boolean = true,
    val isAdditionalLoading: Boolean = false,
)