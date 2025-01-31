package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.core.common.data.di.qualifier.Auth
import com.teamoffroad.feature.explore.data.remote.service.PlaceService
import com.teamoffroad.feature.explore.data.remote.service.QuestService
import com.teamoffroad.feature.explore.data.remote.service.UserService
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
    fun provideUserService(@Auth retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun providePlaceService(@Auth retrofit: Retrofit): PlaceService {
        return retrofit.create(PlaceService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuestService(@Auth retrofit: Retrofit): QuestService {
        return retrofit.create(QuestService::class.java)
    }
}
