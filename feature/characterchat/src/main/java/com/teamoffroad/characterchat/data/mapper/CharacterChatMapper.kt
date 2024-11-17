package com.teamoffroad.characterchat.data.mapper

import com.teamoffroad.characterchat.data.remote.response.CharacterChatResponseDto
import com.teamoffroad.characterchat.domain.model.Chat

fun CharacterChatResponseDto.toDomain(): Chat {
    return Chat(
        role = role,
        content = content,
        createdAt = createdAt,
    )
}
