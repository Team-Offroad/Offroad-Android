package com.teamoffroad.characterchat.presentation.model

enum class ChatType {
    USER, ORB_CHARACTER,
    ;

    companion object {
        fun toChatType(type: String): ChatType {
            return entries.find { it.name == type } ?: USER
        }
    }
}
