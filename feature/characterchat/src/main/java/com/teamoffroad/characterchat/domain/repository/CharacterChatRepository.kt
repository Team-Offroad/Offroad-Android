package com.teamoffroad.characterchat.domain.repository

import com.teamoffroad.characterchat.domain.model.Chat

interface CharacterChatRepository {

    suspend fun fetchChats(characterId: Int?): List<Chat>

    suspend fun saveChat(characterId: Int?, text: String): Chat
}
