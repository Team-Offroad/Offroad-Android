package com.teamoffroad.characterchat.data.repository

import com.teamoffroad.characterchat.data.mapper.toDomain
import com.teamoffroad.characterchat.data.remote.request.CharacterChatSendRequestDto
import com.teamoffroad.characterchat.data.remote.service.ChatService
import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import javax.inject.Inject

class CharacterChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService,
) : CharacterChatRepository {

    override suspend fun fetchChats(characterId: Int): List<Chat> {
        return chatService.getChats().data?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun saveChat(characterId: Int, text: String): Chat {
        // TODO: Implement characterId
        return chatService.sendChat(CharacterChatSendRequestDto(text)).data?.toDomain() ?: Chat("", "", "")
    }
}
