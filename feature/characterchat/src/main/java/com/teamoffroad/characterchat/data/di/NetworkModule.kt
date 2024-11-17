package com.teamoffroad.characterchat.data.di

import com.teamoffroad.characterchat.data.remote.service.ChatService
import com.teamoffroad.core.common.data.di.qualifier.Auth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideChatService(@Auth retrofit: Retrofit): ChatService {
        return retrofit.create(ChatService::class.java)
    }
}
