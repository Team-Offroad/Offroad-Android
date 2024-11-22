package com.teamoffroad.feature.home.data.di

import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.home.domain.repository.FcmTokenRepository
import com.teamoffroad.feature.home.domain.repository.UserRepository
import com.teamoffroad.feature.home.domain.usecase.GetDummyUserListUseCase
import com.teamoffroad.feature.home.domain.usecase.PostFcmTokenUseCase
import com.teamoffroad.feature.home.domain.usecase.UserUseCase
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

    @Provides
    @Singleton
    fun provideGetEmblemListUseCase(
        userRepository: UserRepository,
    ): UserUseCase {
        return UserUseCase(userRepository)
    }

    @Provides
    @Singleton
    fun providePostFcmTokenUseCase(
        fcmTokenRepository: FcmTokenRepository,
    ): PostFcmTokenUseCase {
        return PostFcmTokenUseCase(fcmTokenRepository)
    }
}
