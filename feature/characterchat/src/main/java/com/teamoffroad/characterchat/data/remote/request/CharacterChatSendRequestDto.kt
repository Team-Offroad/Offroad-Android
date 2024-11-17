package com.teamoffroad.characterchat.data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterChatSendRequestDto(
    @SerialName("content")
    val content: String,
)
