package com.teamoffroad.characterchat.domain.repository

import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.model.ChatLastUnread

interface CharacterChatRepository {

    suspend fun fetchChats(characterId: Int?, limit: Int, cursor: Long?): Result<List<Chat>>

    suspend fun saveChat(characterId: Int?, text: String): Chat

    suspend fun fetchChatsLastUnread(): ChatLastUnread
}
