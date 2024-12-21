package com.teamoffroad.characterchat.domain.usecase

import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository

class GetChatListUseCase(
    private val characterChatRepository: CharacterChatRepository,
) {
    suspend operator fun invoke(characterId: Int? = null, limit: Int, cursor: Long? = Long.MAX_VALUE): Result<List<Chat>> {
        return characterChatRepository.fetchChats(characterId, limit, cursor)
    }
}
