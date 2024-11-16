package com.teamoffroad.characterchat.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterChatsResponseDto(
    @SerialName("data")
    val `data`: List<CharacterChatResponseDto>,
)
