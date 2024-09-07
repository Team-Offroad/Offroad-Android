package com.teamoffroad.core.common.data.di

import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.data.local.AuthAuthenticator
import com.teamoffroad.core.common.data.local.AuthInterceptor
import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import dagger.Lazy
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
    fun provideAuthInterceptor(
        authAuthenticator: AuthAuthenticator,
        tokenPreferencesDataSource: TokenPreferencesDataSource,
    ): AuthInterceptor {
        return AuthInterceptor(authAuthenticator, tokenPreferencesDataSource)
    }

    @Provides
    @Singleton
    fun provideAuthAuthenticator(
        tokenPreferencesDataSource: TokenPreferencesDataSource,
        refreshTokenUseCase: Lazy<RefreshTokenUseCase>,
    ): AuthAuthenticator {
        return AuthAuthenticator(tokenPreferencesDataSource, refreshTokenUseCase)
    }
}
