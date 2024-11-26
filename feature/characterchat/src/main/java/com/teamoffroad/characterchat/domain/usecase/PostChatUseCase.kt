package com.teamoffroad.characterchat.domain.usecase

import com.teamoffroad.characterchat.domain.model.Chat
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository

class PostChatUseCase(
    private val characterChatRepository: CharacterChatRepository,
) {
    suspend operator fun invoke(characterId: Int? = null, text: String): Chat {
        return characterChatRepository.saveChat(characterId, text)
    }
}
