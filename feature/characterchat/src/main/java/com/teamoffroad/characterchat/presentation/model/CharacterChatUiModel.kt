package com.teamoffroad.characterchat.presentation.model

data class ChatModel(
    val chatType: ChatType,
    val text: String = "",
    val time: String = "",
)
