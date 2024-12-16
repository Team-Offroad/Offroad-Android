package com.teamoffroad.characterchat.presentation.model

data class CharacterChatLastUnreadUiState(
    val doesAllRead: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
