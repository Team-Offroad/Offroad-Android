package com.teamoffroad.feature.home.data.di

import com.teamoffroad.feature.home.data.repository.DummyDummyUserRepositoryImpl
import com.teamoffroad.feature.home.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.home.domain.repository.UserRepository
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
    abstract fun bindDummyUserRepository(
        dummyUserRepositoryImpl: DummyDummyUserRepositoryImpl
    ): DummyUserRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}
