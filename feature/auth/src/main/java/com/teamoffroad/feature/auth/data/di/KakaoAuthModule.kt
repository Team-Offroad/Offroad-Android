package com.teamoffroad.feature.auth.data.di

import com.kakao.sdk.user.UserApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KakaoAuthModule {
    @Provides
    @Singleton
    fun provideKakaoClient(): UserApiClient = UserApiClient.instance
}