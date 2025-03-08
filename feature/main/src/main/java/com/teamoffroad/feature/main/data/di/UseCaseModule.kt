package com.teamoffroad.feature.main.data.di

import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.home.domain.repository.FcmTokenRepository
import com.teamoffroad.feature.home.domain.repository.UserRepository
import com.teamoffroad.feature.home.domain.usecase.GetDummyUserListUseCase
import com.teamoffroad.feature.home.domain.usecase.PostFcmTokenUseCase
import com.teamoffroad.feature.home.domain.usecase.UserUseCase
import com.teamoffroad.feature.main.domain.repository.MinSupportedVersionRepository
import com.teamoffroad.feature.main.domain.usecase.MinSupportedVersionUseCase
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
    fun provideGetMinSupportedVersionUseCase(
        minSupportedVersionRepository: MinSupportedVersionRepository,
    ): MinSupportedVersionUseCase {
        return MinSupportedVersionUseCase(minSupportedVersionRepository)
    }

}
