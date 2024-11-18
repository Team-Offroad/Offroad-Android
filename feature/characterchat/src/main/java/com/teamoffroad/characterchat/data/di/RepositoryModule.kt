package com.teamoffroad.characterchat.data.di

import com.teamoffroad.characterchat.data.repository.CharacterChatRepositoryImpl
import com.teamoffroad.characterchat.domain.repository.CharacterChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCharacterChatRepository(
        characterChatRepositoryImpl: CharacterChatRepositoryImpl,
    ): CharacterChatRepository
}
