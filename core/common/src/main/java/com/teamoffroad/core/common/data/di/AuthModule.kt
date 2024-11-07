package com.teamoffroad.core.common.data.di

import android.content.Context
import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.data.local.AuthAuthenticator
import com.teamoffroad.core.common.data.local.AuthInterceptor
import com.teamoffroad.core.common.domain.usecase.RefreshTokenUseCase
import com.teamoffroad.core.common.domain.usecase.UpdateAutoSignInUseCase
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        updateAutoSignInUseCase: UpdateAutoSignInUseCase,
        @ApplicationContext context: Context,
    ): AuthAuthenticator {
        return AuthAuthenticator(tokenPreferencesDataSource, refreshTokenUseCase, updateAutoSignInUseCase, context)
    }
}
