package com.teamoffroad.feature.explore.data.di

import com.teamoffroad.feature.explore.data.remote.service.ExploreService
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
    fun provideExploreService(retrofit: Retrofit): ExploreService {
        return retrofit.create(ExploreService::class.java)
    }
}
