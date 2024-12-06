package com.teamoffroad.feature.auth.data.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.kakao.sdk.user.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocialAuthModule {
    @Provides
    @Singleton
    fun provideKakaoClient(): UserApiClient = UserApiClient.instance

    @Provides
    @Singleton
    fun provideGoogleClient(
        @ApplicationContext context: Context
    ): CredentialManager = CredentialManager.create(context)
}