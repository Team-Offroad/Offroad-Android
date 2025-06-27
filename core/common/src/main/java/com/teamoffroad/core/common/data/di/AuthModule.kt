package com.teamoffroad.core.common.data.di

import android.content.Context
import com.teamoffroad.core.common.data.local.AuthAuthenticator
import com.teamoffroad.core.common.data.local.AuthInterceptor
import com.teamoffroad.core.common.data.remote.service.TokenService
import com.teamoffroad.core.common.domain.preferences.TokenPreferences
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
        tokenPreferences: TokenPreferences,
    ): AuthInterceptor = AuthInterceptor(authAuthenticator, tokenPreferences)

    @Provides
    @Singleton
    fun provideAuthAuthenticator(
        tokenPreferences: TokenPreferences,
        refreshTokenUseCase: TokenService,
        setAutoSignInUseCase: SetAutoSignInUseCase,
        @ApplicationContext context: Context,
        intentProvider: IntentProvider,
    ): AuthAuthenticator = AuthAuthenticator(tokenPreferences, refreshTokenUseCase, setAutoSignInUseCase, context, intentProvider)
}
