package com.teamoffroad.characterchat.presentation.model

data class CharacterChatLastUnreadUiState(
    val doesAllRead: Boolean = true,
    val characterName: String? = null,
    val content: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
