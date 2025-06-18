package com.teamoffroad.feature.recommendplace.presentation.model

import com.teamoffroad.characterchat.presentation.model.ChatModel
import java.time.LocalDate

data class PlaceRecommendationsOrderChatUiState(
    val chats: Map<LocalDate, List<ChatModel>> = emptyMap(),
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
