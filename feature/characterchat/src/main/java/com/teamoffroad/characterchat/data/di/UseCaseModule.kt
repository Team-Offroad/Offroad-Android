package com.teamoffroad.characterchat.data.di

import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import com.teamoffroad.characterchat.domain.usecase.GetChatListUseCase
import com.teamoffroad.characterchat.domain.usecase.PostChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetCharacterChatListUseCase(
        characterChatRepository: CharacterChatRepository,
    ): GetChatListUseCase {
        return GetChatListUseCase(characterChatRepository)
    }

    @Provides
    @Singleton
    fun providePostCharacterChatUseCase(
        characterChatRepository: CharacterChatRepository,
    ): PostChatUseCase {
        return PostChatUseCase(characterChatRepository)
    }
}
