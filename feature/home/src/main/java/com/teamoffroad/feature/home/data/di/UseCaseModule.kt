package com.teamoffroad.feature.home.data.di

import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.home.domain.usecase.GetDummyUserListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetDummyUserListUseCase(
        dummyUserRepository: DummyUserRepository,
    ): GetDummyUserListUseCase {
        return GetDummyUserListUseCase(dummyUserRepository)
    }
}
