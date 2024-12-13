package com.teamoffroad.characterchat.domain.model

data class ChatLastUnread(
    val doesAllRead: Boolean,
    val characterName: String?,
    val content: String?
)
