package com.teamoffroad.characterchat.presentation.model

data class UserChattingUiState(
    val chatContent: String = "",
    val showUserChatTextField: Boolean = false,
    val isUserChattingLoading: Boolean = false,
    val isError: Boolean = false
)
