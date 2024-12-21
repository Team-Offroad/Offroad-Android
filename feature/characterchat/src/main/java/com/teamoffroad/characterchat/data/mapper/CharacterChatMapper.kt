package com.teamoffroad.characterchat.data.mapper

import com.teamoffroad.characterchat.data.remote.response.CharacterChatLastUnreadResponseDto
import com.teamoffroad.characterchat.data.remote.response.CharacterChatResponseDto
import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.model.ChatLastUnread

fun CharacterChatResponseDto.toDomain(): Chat {
    return Chat(
        role = role,
        content = content,
        createdAt = createdAt,
    )
}

fun CharacterChatLastUnreadResponseDto.toDomain(): ChatLastUnread {
    return ChatLastUnread(
        doesAllRead = doesAllRead,
        characterName = characterName,
        content = content
    )
}