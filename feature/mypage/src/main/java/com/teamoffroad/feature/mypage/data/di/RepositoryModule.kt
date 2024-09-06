package com.teamoffroad.feature.mypage.data.di

import com.teamoffroad.feature.mypage.data.repository.CharacterRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.EmblemRepositoryImpl
import com.teamoffroad.feature.mypage.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.mypage.domain.repository.CharacterRepository
import com.teamoffroad.feature.mypage.domain.repository.EmblemRepository
import com.teamoffroad.feature.mypage.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository

    @Binds
    abstract fun bindEmblemRepository(
        emblemRepositoryImpl: EmblemRepositoryImpl,
    ): EmblemRepository

    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl,
    ): CharacterRepository
}
