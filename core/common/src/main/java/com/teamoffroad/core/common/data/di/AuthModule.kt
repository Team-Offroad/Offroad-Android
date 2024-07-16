package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.data.local.AuthAuthenticator
import com.teamoffroad.core.common.data.local.AuthInterceptor
import com.teamoffroad.core.common.data.local.TokenManager
import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor {
        return AuthInterceptor(tokenManager)
    }

    @Provides
    @Singleton
    fun provideAuthAuthenticator(
        tokenManager: TokenManager,
        refreshTokenUseCase: RefreshTokenUseCase,
    ): AuthAuthenticator {
        return AuthAuthenticator(tokenManager, refreshTokenUseCase)
    }
}
