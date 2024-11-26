package com.teamoffroad.feature.main

data class UserChattingUiState(
    val chatContent: String = "",
    val showUserChatTextField: Boolean = false,
    val isUserChattingLoading: Boolean = false,
    val isError: Boolean = false
)
