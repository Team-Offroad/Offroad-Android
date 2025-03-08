package com.teamoffroad.feature.main.data.di

import com.teamoffroad.feature.home.data.repository.DummyDummyUserRepositoryImpl
import com.teamoffroad.feature.home.data.repository.FcmTokenRepositoryImpl
import com.teamoffroad.feature.home.data.repository.UserRepositoryImpl
import com.teamoffroad.feature.home.domain.repository.DummyUserRepository
import com.teamoffroad.feature.home.domain.repository.FcmTokenRepository
import com.teamoffroad.feature.home.domain.repository.UserRepository
import com.teamoffroad.feature.main.data.repository.MinSupportedVersionRepositoryImpl
import com.teamoffroad.feature.main.domain.repository.MinSupportedVersionRepository
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
    abstract fun bindMinSupportedVersionRepository(
        minSupportedVersionRepositoryImpl: MinSupportedVersionRepositoryImpl
    ): MinSupportedVersionRepository

}
