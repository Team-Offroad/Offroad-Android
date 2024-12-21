package com.teamoffroad.characterchat.domain.usecase

import com.teamoffroad.characterchat.domain.model.ChatLastUnread
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository

class GetChatLastUnreadUseCase(
    private val characterChatRepository: CharacterChatRepository
) {
    suspend operator fun invoke(): ChatLastUnread {
        return characterChatRepository.fetchChatsLastUnread()
    }
}