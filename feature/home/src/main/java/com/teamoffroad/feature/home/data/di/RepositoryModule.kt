package com.teamoffroad.feature.home.data.di

import com.teamoffroad.feature.home.data.repository.DummyDummyUserRepositoryImpl
import com.teamoffroad.feature.home.data.repository.EmblemRepositoryImpl
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.home.domain.repository.EmblemRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        dummyUserRepositoryImpl: DummyDummyUserRepositoryImpl
    ): DummyUserRepository

    @Binds
    @Singleton
    abstract fun bindEmblemRepository(
        emblemRepositoryImpl: EmblemRepositoryImpl
    ): EmblemRepository
}
