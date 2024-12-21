package com.teamoffroad.characterchat.presentation.mapper

import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.presentation.model.ChatModel
import com.teamoffroad.characterchat.presentation.model.ChatModel.Companion.toDate
import com.teamoffroad.characterchat.presentation.model.ChatModel.Companion.toTime
import com.teamoffroad.characterchat.presentation.model.ChatType

fun Chat.toUi(): ChatModel {
    return ChatModel(
        chatType = ChatType.toChatType(role),
        text = content,
        date = createdAt.toDate(),
        time = createdAt.toTime(),
        id = id,
    )
}
