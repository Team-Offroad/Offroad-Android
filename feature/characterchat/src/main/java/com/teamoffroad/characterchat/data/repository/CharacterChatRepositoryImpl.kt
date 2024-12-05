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

    override suspend fun fetchChats(characterId: Int?, limit: Int, cursor: Int?): Result<List<Chat>> {
        return runCatching {
            chatService.getChats(characterId, limit, cursor).data?.map { it.toDomain() } ?: emptyList()
        }
    }

    override suspend fun saveChat(characterId: Int?, text: String): Chat {
        return chatService.sendChat(characterId, CharacterChatSendRequestDto(text)).data?.toDomain() ?: Chat("", "", "")
    }
}
