package com.teamoffroad.feature.recommendplace.presentation.model

import com.teamoffroad.characterchat.presentation.model.ChatModel
import java.time.LocalDate

data class PlaceRecommendationsChatUiState(
    val chats: Map<LocalDate, List<ChatModel>> = emptyMap(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
