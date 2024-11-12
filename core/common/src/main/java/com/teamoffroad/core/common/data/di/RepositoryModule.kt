package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.data.repository.AutoSignInRepositoryImpl
import com.teamoffroad.core.common.data.repository.DeviceTokenRepositoryImpl
import com.teamoffroad.core.common.data.repository.TokenRepositoryImpl
import com.teamoffroad.core.common.domain.repository.AutoSignInRepository
import com.teamoffroad.core.common.domain.repository.DeviceTokenRepository
import com.teamoffroad.core.common.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTokenRepository(
        tokenRepositoryImpl: TokenRepositoryImpl,
    ): TokenRepository

    @Binds
    @Singleton
    abstract fun bindAutoSignInRepository(
        authRepositoryImpl: AutoSignInRepositoryImpl,
    ): AutoSignInRepository

    @Binds
    @Singleton
    abstract fun bindDeviceTokenRepository(
        deviceTokenRepositoryImpl: DeviceTokenRepositoryImpl
    ): DeviceTokenRepository
}
