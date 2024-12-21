package com.teamoffroad.characterchat.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterChatLastUnreadResponseDto(
    @SerialName("doesAllRead")
    val doesAllRead: Boolean,

    @SerialName("characterName")
    val characterName: String?,

    @SerialName("content")
    val content: String?,
)
