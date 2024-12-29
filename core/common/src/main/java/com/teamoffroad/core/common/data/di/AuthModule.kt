package com.teamoffroad.core.common.data.di

import android.content.Context
import com.teamoffroad.core.common.data.datasource.TokenPreferencesDataSource
import com.teamoffroad.core.common.data.local.AuthAuthenticator
import com.teamoffroad.core.common.data.local.AuthInterceptor
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.usecase.SetAutoSignInUseCase
import com.teamoffroad.core.common.util.IntentProvider
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
        refreshTokenUseCase: TokenService,
        setAutoSignInUseCase: SetAutoSignInUseCase,
        @ApplicationContext context: Context,
        intentProvider: IntentProvider,
    ): AuthAuthenticator {
        return AuthAuthenticator(tokenPreferencesDataSource, refreshTokenUseCase, setAutoSignInUseCase, context, intentProvider)
    }
}
